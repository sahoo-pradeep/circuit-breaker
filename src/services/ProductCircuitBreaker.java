package services;

import request.APIRequest;
import response.APIResponse;
import response.HttpResponse;
import services.api.CircuitBreaker;
import services.api.UpstreamService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Queue;

public class ProductCircuitBreaker implements CircuitBreaker {
    private final static ProductCircuitBreaker INSTANCE = new ProductCircuitBreaker();

    private static final int MAX_ERRORS; //N
    private static final long BLOCK_TIME_MILLIS; //T
    private static final long WAIT_TIME_MILLIS; //X

    private UpstreamService productUpstreamService = ProductUpstreamService.getInstance();

    private Queue<APIResponse> queue = new LinkedList<>();
    private static final APIResponse customError;
    private LocalDateTime blockTimestamp;

    // Circuit Breaker Status
    private boolean open = true;

    static {
        // Configuration
        MAX_ERRORS = 3;
        BLOCK_TIME_MILLIS = 5 * 1000; // 5 Seconds
        WAIT_TIME_MILLIS = 5 * 1000; // 5 Seconds

        customError = new APIResponse(HttpResponse.SERVICE_UNAVAILABLE, null);
    }

    private ProductCircuitBreaker() {
    }

    public static ProductCircuitBreaker getInstance() {
        return INSTANCE;
    }

    public synchronized APIResponse get(APIRequest request) {
        System.out.println("Queue before: " + queue);
        if (queue.size() == MAX_ERRORS && !open) {
            // Wait Time is not over
            if (LocalDateTime.now().minus(WAIT_TIME_MILLIS, ChronoUnit.MILLIS)
                    .compareTo(blockTimestamp) < 0) {
                System.out.println("Queue after: " + queue);
                return customError;
            } else {
                // Wait Time is over
                while (!queue.isEmpty()) {
                    queue.poll();
                }
                open = true;
            }
        }

        APIResponse response = productUpstreamService.get(request);

        switch (response.getHttpResponse()) {
            case INTERNAL_SERVER:
            case SERVICE_UNAVAILABLE:
            case BAD_GATEWAY:
                handle5xxError(response);
                break;
            case OK:
                handle2xxResponse();
                break;
        }

        System.out.println("Queue after: " + queue);
        return response;
    }

    public String getStatus() {
        if (open) {
            return "RUNNING";
        } else {
            return "BLOCKED";
        }
    }

    private void handle5xxError(APIResponse response) {
        if (queue.size() > 0) {
            // Block Range: current time to current minus BLOCK_TIME_MILLIS
            LocalDateTime blockMin =
                    LocalDateTime.now().minus(BLOCK_TIME_MILLIS, ChronoUnit.MILLIS);

            // All response below blockMin will be removed from queue
            while (!queue.isEmpty()) {
                if (queue.peek().getTimestamp().compareTo(blockMin) < 0) {
                    queue.poll();
                } else {
                    break;
                }
            }
        }

        queue.offer(response);

        if (queue.size() == MAX_ERRORS) {
            blockTimestamp = LocalDateTime.now();
            open = false;
        }
    }

    private void handle2xxResponse() {
        while (!queue.isEmpty()) {
            queue.poll();
        }
    }
}

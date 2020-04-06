package services;

import request.APIRequest;
import response.APIResponse;
import services.api.CircuitBreaker;

import java.util.List;

public class DownstreamService {
    private CircuitBreaker circuitBreakerImpl = ProductCircuitBreaker.getInstance();

    public void handleRequest(List<APIRequest> requests) {
        APIResponse response;
        int count = 1;
        for (APIRequest request : requests) {
            response = circuitBreakerImpl.get(request);
            System.out.println(count + ". [" + response.getTimestamp() + "] id: " + request.getId()
                    + " , Status: " + circuitBreakerImpl.getStatus());
            System.out.println();
            count++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

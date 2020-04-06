package testCase;

import request.APIRequest;
import request.MethodType;
import services.DownstreamService;

import java.util.ArrayList;
import java.util.List;

public class DownstreamServiceTest {
    private static DownstreamService downstreamService = new DownstreamService();
    private static final String URL = "getProduct";

    public static void main(String[] args) {
        set1();
        //set2();
    }

    private static void set1(){
        System.out.println("Set 1 Running\n--------------------\n");
        List<APIRequest> requests = new ArrayList<>();
        requests.add(new APIRequest(MethodType.GET, URL, 0)); //OK
        requests.add(new APIRequest(MethodType.GET, URL, 1)); //5XX
        requests.add(new APIRequest(MethodType.GET, URL, 2)); //5XX
        requests.add(new APIRequest(MethodType.GET, URL, 3)); //5XX

        requests.add(new APIRequest(MethodType.GET, URL, 0)); //OK BLOCKED
        requests.add(new APIRequest(MethodType.GET, URL, 0)); //OK BLOCKED
        requests.add(new APIRequest(MethodType.GET, URL, 0)); //OK BLOCKED
        requests.add(new APIRequest(MethodType.GET, URL, 0)); //OK BLOCKED
        requests.add(new APIRequest(MethodType.GET, URL, 0)); //OK RUNNING/BLOCKED
        requests.add(new APIRequest(MethodType.GET, URL, 0)); //OK RUNNING
        requests.add(new APIRequest(MethodType.GET, URL, 1)); //5XX RUNNING Queue.size = 1
        downstreamService.handleRequest(requests);
        System.out.println("\n-------------------\nSet 1 Finished\n---------------------\n");
    }

    private static void set2(){
        List<APIRequest> requests = new ArrayList<>();
        requests.add(new APIRequest(MethodType.GET, URL, 0)); //OK
        requests.add(new APIRequest(MethodType.GET, URL, 1)); //5XX
        requests.add(new APIRequest(MethodType.GET, URL, 2)); //5XX
        requests.add(new APIRequest(MethodType.GET, URL, 0)); //OK EMPTY QUEUE
        requests.add(new APIRequest(MethodType.GET, URL, 3)); //5XX
        requests.add(new APIRequest(MethodType.GET, URL, 0)); //OK
        requests.add(new APIRequest(MethodType.GET, URL, 0)); //OK

        downstreamService.handleRequest(requests);
    }
}

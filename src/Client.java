import request.APIRequest;
import request.MethodType;
import services.DownstreamService;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private static final String URL = "getProduct";

    public static void main(String[] args) {
        List<APIRequest> requests = new ArrayList<>();
        requests.add(new APIRequest(MethodType.GET, URL, 0));
        requests.add(new APIRequest(MethodType.GET, URL, 1));
        requests.add(new APIRequest(MethodType.GET, URL, 2));
        requests.add(new APIRequest(MethodType.GET, URL, 3));
        requests.add(new APIRequest(MethodType.GET, URL, 0));
        requests.add(new APIRequest(MethodType.GET, URL, 0));
        requests.add(new APIRequest(MethodType.GET, URL, 0));
        requests.add(new APIRequest(MethodType.GET, URL, 0));
        requests.add(new APIRequest(MethodType.GET, URL, 0));
        requests.add(new APIRequest(MethodType.GET, URL, 0));

        DownstreamService downstreamService = new DownstreamService();
        downstreamService.handleRequest(requests);

    }
}

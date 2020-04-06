package testCase;

import request.APIRequest;
import request.MethodType;
import services.ProductUpstreamService;
import services.api.UpstreamService;

public class ProductUpstreamServiceTest {
    private static UpstreamService productUpstreamService = ProductUpstreamService.getInstance();
    private static final String URL = "getProduct";


    public static void main(String[] args) {
        System.out.println("Id = 0, " + productUpstreamService.get(new APIRequest(MethodType.GET, URL, 0)));
        System.out.println("Id = 1," + productUpstreamService.get(new APIRequest(MethodType.GET, URL, 1)));
        System.out.println("Id = 2, " + productUpstreamService.get(new APIRequest(MethodType.GET, URL, 2)));
        System.out.println("Id = 3, " + productUpstreamService.get(new APIRequest(MethodType.GET, URL, 3)));
        System.out.println("Id = 4, " + productUpstreamService.get(new APIRequest(MethodType.GET, URL, 4)));
    }
}

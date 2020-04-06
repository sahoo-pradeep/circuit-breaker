package services;

import entity.Product;
import request.APIRequest;
import response.APIResponse;
import response.HttpResponse;
import services.api.UpstreamService;

public class ProductUpstreamService implements UpstreamService {
    private final static ProductUpstreamService INSTANCE = new ProductUpstreamService();

    private ProductUpstreamService() {
    }

    public static ProductUpstreamService getInstance() {
        return INSTANCE;
    }

    public APIResponse get(APIRequest request) {
        Integer id = request.getId();
        switch (id) {
            case 1:
                return new APIResponse(HttpResponse.INTERNAL_SERVER, null);
            case 2:
                return new APIResponse(HttpResponse.BAD_GATEWAY, null);
            case 3:
                return new APIResponse(HttpResponse.SERVICE_UNAVAILABLE, null);
            default:
                return new APIResponse(HttpResponse.OK, new Product(id, "ABC").toString());
        }
    }
}

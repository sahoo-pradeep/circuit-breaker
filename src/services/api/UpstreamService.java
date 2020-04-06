package services.api;

import request.APIRequest;
import response.APIResponse;

public interface UpstreamService {
    APIResponse get(APIRequest request);
}

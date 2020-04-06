package services.api;

import request.APIRequest;
import response.APIResponse;

public interface CircuitBreaker {
    APIResponse get(APIRequest request);

    String getStatus();
}

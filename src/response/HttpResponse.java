package response;

public enum HttpResponse {
    OK(200),
    INTERNAL_SERVER(500),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(504);

    int code;

    HttpResponse(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

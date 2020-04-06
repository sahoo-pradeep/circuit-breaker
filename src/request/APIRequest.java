package request;

public class APIRequest {
    private MethodType methodType;
    private String url;
    private Integer id;

    public APIRequest(MethodType methodType, String url, Integer id) {
        this.methodType = methodType;
        this.url = url;
        this.id = id;
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

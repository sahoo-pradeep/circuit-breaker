package response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class APIResponse {
    private static final String DATE_FORMAT = "HH:mm:ss";
    private HttpResponse httpResponse;
    private String body;
    private LocalDateTime timestamp;

    public APIResponse(HttpResponse httpResponse, String body) {
        this.httpResponse = httpResponse;
        this.body = body;
        timestamp = LocalDateTime.now();
    }

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "Response [", "]");
        joiner.add(timestamp.format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        joiner.add(httpResponse.name());
        return joiner.toString();
    }
}

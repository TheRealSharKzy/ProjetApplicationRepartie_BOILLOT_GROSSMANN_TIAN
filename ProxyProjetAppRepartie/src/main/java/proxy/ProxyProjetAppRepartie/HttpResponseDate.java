package proxy.ProxyProjetAppRepartie;

import java.io.Serializable;

public class HttpResponseDate implements Serializable {
    private static final long serialVersionUID = 1L;
    private int statusCode;
    private String body;
    private String contentType;

    public HttpResponseDate(int statusCode, String body, String contentType) {
        this.statusCode = statusCode;
        this.body = body;
        this.contentType = contentType;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public String getContentType() {
        return contentType;
    }
}

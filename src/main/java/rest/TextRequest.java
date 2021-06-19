package rest;

import core.ZOSConnection;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import utility.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextRequest implements IZoweRequest {

    private ZOSConnection connection;
    private HttpGet getRequest;
    private HttpPut putRequest;
    private String body;
    private Map<String, String> headers = new HashMap<>();
    private HttpClient client = HttpClientBuilder.create().build();
    private ResponseHandler<String> handler = new BasicResponseHandler();

    public TextRequest(ZOSConnection connection, HttpGet getRequest) {
        this.connection = connection;
        this.getRequest = getRequest;
    }

    public TextRequest(ZOSConnection connection, HttpPut putRequest, String body) {
        this.connection = connection;
        this.putRequest = putRequest;
        this.body = body;
    }

    @Override
    public <T> T httpGet() throws IOException {
        this.setStandardHeaders();
        if (!headers.isEmpty()) headers.entrySet().stream().forEach(e -> getRequest.setHeader(e.getKey(), e.getValue()));

        return (T) client.execute(getRequest, handler);
    }

    @Override
    public <T> T httpPut() throws IOException {
        this.setStandardHeaders();
        if (!headers.isEmpty()) headers.entrySet().stream().forEach(e -> putRequest.setHeader(e.getKey(), e.getValue()));
        putRequest.setEntity(new StringEntity(body));

        return (T) client.execute(putRequest, handler);
    }

    private void setStandardHeaders() {
        String key = ZosmfHeaders.HEADERS.get(ZosmfHeaders.X_CSRF_ZOSMF_HEADER).get(0);
        String value = ZosmfHeaders.HEADERS.get(ZosmfHeaders.X_CSRF_ZOSMF_HEADER).get(1);

        if (putRequest != null) {
            putRequest.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + Util.getAuthEncoding(connection));
            putRequest.setHeader("Content-Type", "text/plain; charset=UTF-8");
            putRequest.setHeader(key, value);
        }
        if (getRequest != null) {
            getRequest.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + Util.getAuthEncoding(connection));
            getRequest.setHeader("Content-Type", "text/plain; charset=UTF-8");
            getRequest.setHeader(key, value);
        }
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

}

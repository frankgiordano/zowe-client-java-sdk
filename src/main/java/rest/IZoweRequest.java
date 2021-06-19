package rest;

import java.io.IOException;
import java.util.Map;

public interface IZoweRequest {

    public <T> T httpGet() throws IOException;

    public <T> T httpPut() throws IOException;

    public void setHeaders(Map<String, String> headers);

}

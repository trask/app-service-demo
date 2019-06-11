package browser;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Application {

    public static void main(String[] args) throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        get(httpClient, "http://localhost:8080");
        get(httpClient, "http://localhost:8080/service-a");
        get(httpClient, "http://localhost:8080/service-b");
        get(httpClient, "http://localhost:8080/service-c");
        get(httpClient, "http://localhost:8080/service-ab");
        get(httpClient, "http://localhost:8080/service-bc");
        get(httpClient, "http://localhost:8080/service-abc");
    }

    private static void get(CloseableHttpClient httpClient, String uri) throws Exception {
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        EntityUtils.consume(response.getEntity());
        response.close();
    }
}

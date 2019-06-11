package frontend;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.CharStreams;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/service-a")
    public String serviceA() throws IOException {
        return get("http://localhost:8081");
    }

    @RequestMapping("/service-b")
    public String serviceB() throws IOException {
        return get("http://localhost:8082");
    }

    @RequestMapping("/service-c")
    public String serviceC() throws IOException {
        return get("http://localhost:8083");
    }

    @RequestMapping("/service-ab")
    public String serviceAB() throws IOException {
        return get("http://localhost:8081/service-b");
    }

    @RequestMapping("/service-ac")
    public String serviceAC() throws IOException {
        return get("http://localhost:8081/service-c");
    }

    @RequestMapping("/service-bc")
    public String serviceBC() throws IOException {
        return get("http://localhost:8082/service-c");
    }

    @RequestMapping("/service-abc")
    public String serviceABC() throws IOException {
        return get("http://localhost:8081/service-bc");
    }

    private static String get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        InputStream content = response.getEntity().getContent();
        String body = CharStreams.toString(new InputStreamReader(content));
        content.close();
        httpClient.close();
        return body;
    }
}

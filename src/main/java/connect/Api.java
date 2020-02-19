package connect;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;



public class Api {
    private String urlRoot;
    private String token;

    public Api(String urlRoot, String xToken) {
        this.urlRoot = urlRoot;
        this.token = xToken;

    }


    public String post(String url, String payload) throws IOException {
        StringBuffer result = new StringBuffer();
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(urlRoot+url);

        StringEntity entity = null;
        if (payload != null) {
            try {
                entity = new StringEntity(payload);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        request.setEntity(entity);
        request.setHeader("X-Token", token);
        HttpResponse response = client.execute(request);
        return result.toString();
    }


}



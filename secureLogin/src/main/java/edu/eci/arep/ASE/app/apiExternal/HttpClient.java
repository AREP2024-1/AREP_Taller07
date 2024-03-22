package edu.eci.arep.ASE.app.apiExternal;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import okhttp3.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.eci.arep.ASE.app.domain.Login;

public class HttpClient {

    private static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient httpClient;

    public HttpClient() {
        httpClient = new OkHttpClient();
    }

    public String call(String url, Login dataLogin){
        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder().build();
        ObjectMapper mapper = new ObjectMapper();
        
        String dataAux = "";
        try{
            RequestBody requestBody = RequestBody.create(mapper.writeValueAsString(dataLogin), JSON);
            Request request = new Request.Builder().url(httpUrl).post(requestBody).build();
            Response response = httpClient.newCall(request).execute();
            dataAux = response.body().string();
        }catch(IOException e){
            e.printStackTrace();
        }
        return dataAux;
    }
}

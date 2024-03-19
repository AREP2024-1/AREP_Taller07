package edu.eci.arep.ASE.app.apiExternal;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.eci.arep.ASE.app.domain.Login;

public class HttpClient {

    private OkHttpClient httpClient;

    public HttpClient() {
        httpClient = new OkHttpClient();
    }

    public String call(String url, Login dataLogin){
        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder().build();
        ObjectMapper mapper = new ObjectMapper();
        Request request = new Request.Builder().url(httpUrl).post(mapper.writeValueAsString(dataLogin)).build();
        String dataAux = "";
        try(Response response = httpClient.newCall(request).execute()){
            dataAux = response.body().string();
        }catch(IOException e){
            e.printStackTrace();
        }

        return dataAux;

    }
}


public class HttpClient {
    
}

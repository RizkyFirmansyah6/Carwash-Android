package com.projctwash.com.proyek2_carwash.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //projectcarwashservice.000webhostapp.com/index.php/
    public static final String BASE_URL = "http://192.168.88.135/carwash_ci/web_service/index.php/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}

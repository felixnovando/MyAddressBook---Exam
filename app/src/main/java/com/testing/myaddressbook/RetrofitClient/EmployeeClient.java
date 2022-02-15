package com.testing.myaddressbook.RetrofitClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeClient {

//    https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com/stage2/people/?nim=2301859543&nama=Felix Novando

    private static Retrofit clientInstance = null;
    public static final String URL = "https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com";

    public static Retrofit getClientInstance(){
        if(clientInstance == null){
            clientInstance = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return clientInstance;
    }


}

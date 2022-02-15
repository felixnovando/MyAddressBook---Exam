package com.testing.myaddressbook.RetrofitInterface;

import com.testing.myaddressbook.Models.GetEmployee;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeInterface{

//    https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com/stage2/people/?nim=2301859543&nama=FelixNovando

    @GET("stage2/people/?nim=2301859543&nama=Felix Novando")
    Call<GetEmployee> getEmployeeList();


}

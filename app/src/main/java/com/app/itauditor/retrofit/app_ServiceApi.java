package com.app.itauditor.retrofit;

import com.app.itauditor.model.Response_User_Login;
import com.app.itauditor.model.Response_User_Registration;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Manish Ahire on 01,February,2020
 */
public interface app_ServiceApi {

    // Login API...
  /*
    @GET("VerifyMobile")
    Call<RespMobileOTPVerifi> userOTPVerification(@Query("Mobile") String Mobile);

    @POST("Check_Credentials")
    Call<Response_UserLogin> userLogin(@Body JsonObject jsonBody);

    @GET("Get_Products_Range")
    Call<Response_Get_Products_Range> Get_Products_Range(@Query("Seed") int Seed, @Query("Increment") int Increment);*/

    @POST("auth/register")
    Call<Response_User_Registration> userRegistration(@Body JsonObject jsonBody);

    @POST("auth/login")
    Call<Response_User_Login> userLogin(@Body JsonObject jsonBody);

}

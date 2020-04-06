package com.app.itauditor.retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.app.itauditor.constants.Constants;
import com.app.itauditor.model.Response_User_Login;
import com.app.itauditor.model.Response_User_Registration;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Manish Ahire on 01,February,2020
 */

public class app_ApiCall {
    private Activity mActivity;
    private Context mContext;
    private RequestNotifier mNotifier;
    private String mToken;
    AlertDialog alertDialog;
    private ConnectionDetector mConnectionDetector;

    JsonObject jsonRequest = new JsonObject();


    public app_ApiCall(Activity mActivity, RequestNotifier mNotifier) {
        this.mActivity = mActivity;
        this.mNotifier = mNotifier;
    }

    public app_ApiCall(Context mContext1, RequestNotifier mNotifier1) {
        this.mContext = mContext1;
        this.mNotifier = mNotifier1;
    }

    private  OkHttpClient.Builder initHttpClient() {
        mToken = getdata();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(logging).build();// when makig live comment this link
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request =
                        chain.request().newBuilder()
                                .addHeader("Token", mToken)
                                .build();

                System.out.println("Manishtoken:-   "+mToken);
                return chain.proceed(request);
            }
        }).build();
        return httpClient;
    }

    public String getdata()
    {
        mToken = "manish";
        return  mToken;
    }

    public void NotToken()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Your Session is Expired");
        builder.setMessage("Please Log In Again");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }



    public void userLogin(String username, String password) {
        try {
            Retrofit mRetrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(initHttpClient().build())
                    .baseUrl(Constants.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            jsonRequest.addProperty("username", username);
            jsonRequest.addProperty("password", password);

            System.out.println("jsonRequest "+jsonRequest.toString());

            app_ServiceApi app_serviceApi = mRetrofit.create(app_ServiceApi.class);
            Call<Response_User_Login> mLoginCall = app_serviceApi.userLogin(jsonRequest);
            mLoginCall.enqueue(new Callback<Response_User_Login>() {
                @Override
                public void onResponse(@NonNull Call<Response_User_Login> call, @NonNull Response<Response_User_Login> response) {

                    if(response.code() == 200)
                    {
                        mNotifier.notifySuccess(response,response.code());
                    }else if(response.code() == 401)
                    {
                        mNotifier.notifySuccess(response,response.code());
                    }else {
                        mNotifier.notifySuccess(response,response.code());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Response_User_Login> call, @NonNull Throwable t) {
                    Log.e("API_Error  ", "Error  " + t.getMessage());

                    mNotifier.notifyError(t);
                }
            });
        } catch (Exception e) {
            Log.e("JSSOOONNN  ", "Error  " + e.getMessage());

            e.printStackTrace();
        }
    }



    //Todo Api for Registration
    public void userRegistration(int id, String userName, String emailId, String password) {
        try {
            Retrofit mRetrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(initHttpClient().build())
                    .baseUrl(Constants.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());
            String registered_on = sdf.format(new Date());

            jsonRequest.addProperty("id", id);
            jsonRequest.addProperty("email", emailId);
            jsonRequest.addProperty("username", userName);
            jsonRequest.addProperty("password", password);
            jsonRequest.addProperty("registered_on", registered_on);

            System.out.println("UserRegistration jsonRequest "+jsonRequest.toString());


            app_ServiceApi app_serviceApi = mRetrofit.create(app_ServiceApi.class);
            Call<Response_User_Registration> mLoginCall = app_serviceApi.userRegistration(jsonRequest);
            mLoginCall.enqueue(new Callback<Response_User_Registration>() {
                @Override
                public void onResponse(@NonNull Call<Response_User_Registration> call, @NonNull Response<Response_User_Registration> response) {

                    if(response.message().equals("Invalid Request"))
                    {
                        NotToken();
                    }else {
                        mNotifier.notifySuccess(response,response.code());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Response_User_Registration> call, @NonNull Throwable t) {
                    Log.e("API_Error  ", "Error Response_User_Registration " + t.getMessage());

                    mNotifier.notifyError(t);
                }
            });
        } catch (Exception e) {
            Log.e("JSSOOONNN  ", "Error Response_User_Registration " + e.getMessage());

            e.printStackTrace();
        }
    }





  /*  public void userForgotPassword(String mobile_no) {
        try {
            Retrofit mRetrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(initHttpClient().build())
                    .baseUrl(Constants.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            jsonRequest.addProperty("Mobile", mobile_no);

            System.out.println("jsonRequest "+jsonRequest.toString());



            app_ServiceApi app_serviceApi = mRetrofit.create(app_ServiceApi.class);
            Call<Response_User_Login> mLoginCall = app_serviceApi.userLogin(jsonRequest);
            mLoginCall.enqueue(new Callback<Response_User_Login>() {
                @Override
                public void onResponse(@NonNull Call<Response_User_Login> call, @NonNull Response<Response_User_Login> response) {

                    if(response.message().equals("Invalid Request"))
                    {
                        NotToken();
                    }else {
                        mNotifier.notifySuccess(response);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Response_User_Login> call, @NonNull Throwable t) {
                    Log.e("API_Error  ", "Error  " + t.getMessage());

                    mNotifier.notifyError(t);
                }
            });
        } catch (Exception e) {
            Log.e("JSSOOONNN  ", "Error  " + e.getMessage());

            e.printStackTrace();
        }
    }




    public void userOTPVerification(String mobileNumber) {
        try {
            Retrofit mRetrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(initHttpClient().build())
                    .baseUrl(Constants.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            app_ServiceApi app_serviceApi = mRetrofit.create(app_ServiceApi.class);
            Call<RespMobileOTPVerifi> mLoginCall = app_serviceApi.userOTPVerification(mobileNumber);
            mLoginCall.enqueue(new Callback<RespMobileOTPVerifi>() {
                @Override
                public void onResponse(@NonNull Call<RespMobileOTPVerifi> call, @NonNull Response<RespMobileOTPVerifi> response) {

                    if(response.message().equals("Invalid Request"))
                    {
                        NotToken();
                    }else {
                        mNotifier.notifySuccess(response);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RespMobileOTPVerifi> call, @NonNull Throwable t) {
                    Log.e("API_Error  ", "Error RespMobileOTPVerifi " + t.getMessage());

                    mNotifier.notifyError(t);
                }
            });
        } catch (Exception e) {
            Log.e("JSSOOONNN  ", "Error RespMobileOTPVerifi " + e.getMessage());

            e.printStackTrace();
        }
    }



    public void Get_Products_Range(int Seed,int Increment) {
        try {
            Retrofit mRetrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(initHttpClient().build())
                    .baseUrl(Constants.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            app_ServiceApi mServiceApi = mRetrofit.create(app_ServiceApi.class);
            Call<Response_Get_Products_Range> mGetVehicleListResponseCall = mServiceApi.Get_Products_Range(Seed, Increment);
            mGetVehicleListResponseCall.enqueue(new Callback<Response_Get_Products_Range>() {
                @Override
                public void onResponse(@NonNull Call<Response_Get_Products_Range> call, @NonNull Response<Response_Get_Products_Range> response) {

                    if(response.message().equals("Invalid Request"))
                    {
                        NotToken();
                    }else {
                        mNotifier.notifySuccess(response);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Response_Get_Products_Range> call, @NonNull Throwable t) {
                    Log.e("API_Error  ", "Error Response_Get_Products_Range " + t.getMessage());
                    mNotifier.notifyError(t);
                }
            });

        } catch (Exception e) {
            Log.e("JsonError  ", "Error  Response_Get_Products_Range" + e.getMessage());

            e.printStackTrace();
        }
    }*/





}


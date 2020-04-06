package com.app.itauditor.constants;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.itauditor.model.Response_User_Login;
import com.google.gson.Gson;


/**
 * Created by Manish Ahire on 25,January,2020
 */
public class app_PreferenceManager {

    private  SharedPreferences pref;
    private  SharedPreferences.Editor editor;
    private Context _context;
    private Gson gson ;


    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "intro_slider-welcome";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private final String USER_DATA = "USER_DATA";
    private final String IS_USER_LOGGED = "IS_USER_LOGGED";




    public app_PreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        gson = new Gson();
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }



    public void setUserLogged(boolean isUserLogged){
        editor.putBoolean(IS_USER_LOGGED, isUserLogged);
        editor.commit();
    }

    public boolean getIsUserLogged(){
        boolean isLogged = pref.getBoolean("IS_USER_LOGGED", false);
        return  isLogged;
    }


    public Response_User_Login getLoginData() {
        String userData = pref.getString(USER_DATA, "");
        return gson.fromJson(userData, Response_User_Login.class);
    }

    public void setLoginData(Response_User_Login user){
        String userJson = gson.toJson(user);

        System.out.println(" bciciuuiib "+userJson);
        editor.putString(USER_DATA, userJson);
        editor.commit();
    }



}

package com.app.itauditor.utills;

import android.content.Context;

/**
 * Created by Manish Ahire on 08,February,2020
 */
public class chk_String_Operation {



    Context context;

    public chk_String_Operation(Context context) {
        this.context = context;
    }

    public static boolean isStringNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

    public static boolean isValidEmail(String email) {
        return !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



}

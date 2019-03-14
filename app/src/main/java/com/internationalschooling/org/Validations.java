package com.internationalschooling.org;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Patterns;

public class Validations {
    Context context;
    public static boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean isValidPass(String pass){
        if(pass.length()>5){
            return true;
        }
        else
        { return false;   }

    }






}

package com.example.abacusup.Custom;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;

public class SampleApplication extends Application {

    private static Context appContext;
    public static boolean activityVisible; // variable to check current activity state
    public static final String API_KEY = "";
    public static final String BASE_URL = "http://192.168.1.8/abacussapiiii/api/values";
   // public static final String BASE_URL = "http://192.168.43.2:1321/api/values";

    public void setAppContext(Context mAppContext) {
        this.appContext = mAppContext;
    }

    public static void showMessage(String title, String message, Context c) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(c);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .show();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        this.setAppContext(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static void displayToast(String text, Context c) {
        Toast.makeText(c, text, Toast.LENGTH_SHORT).show();
    }

   }

package com.app.itauditor.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.app.itauditor.MainActivity;
import com.app.itauditor.R;


public class SplashActivity extends AppCompatActivity {
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
//    private app_PreferenceManager app_preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

//        app_preferenceManager = new app_PreferenceManager(SplashActivity.this);

        callPermission();

    }

    private void callPermission() {

        try {
            //Todo Android Multiple Permissions for the App
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED ) {

                    ActivityCompat.requestPermissions(SplashActivity.this, new String[]{
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.INTERNET,
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.READ_CONTACTS,
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.WRITE_CONTACTS,
                                    Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                }else {
                    CallActivity();
                }
            } else {
                CallActivity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                callPermission();
            } else if (grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                callPermission();
            } else {
                CallActivity();
            }
        } else {
            Log.i("Permission", "not Granted");
        }
    }

    private void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure,\n You don't want to access SMS, Network and other permission");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        builder.create();
        builder.show();
    }

    private void CallActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;
               /* if (app_preferenceManager.isFirstTimeLaunch()) {
                    intent = new Intent(Activity_Splash.this, Activity_Welcome.class);
                }else if (!app_preferenceManager.getIsUserLogged()) {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }else {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }*/
                intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 3 * 1000); // wait for 5 seconds

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

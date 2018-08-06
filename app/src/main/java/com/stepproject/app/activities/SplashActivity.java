package com.stepproject.app.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.stepproject.app.R;
import com.stepproject.app.utils.SharedPreferenceValue;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPreferenceValue.getLoggedinUser(SplashActivity.this) != -1) {
                    startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 2000);
        //checkPermission();
    }

    /*RUNTIME PERMISSION CHECKING METHOD*/
    boolean isPermissionGranted = false;
    void checkPermission() {

        Permissions.check(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                        Manifest.permission.READ_LOGS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                getString(R.string.permisson_text), new Permissions.Options()
                        .setSettingsDialogTitle("Warning!").setRationaleDialogTitle("Info"),
                new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        //do your task
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (SharedPreferenceValue.getLoggedinUser(SplashActivity.this) != -1) {
                                    startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                                } else {
                                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                }
                                finish();
                            }
                        }, 2000);
                    }

                    @Override
                    public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                        super.onDenied(context, deniedPermissions);
                        checkPermission();
                    }

                });
    }

}

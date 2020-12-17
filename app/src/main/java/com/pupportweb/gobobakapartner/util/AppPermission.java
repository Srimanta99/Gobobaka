package com.pupportweb.gobobakapartner.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

/**
 * Created by appzoro on 03/05/18.
 **/

public class AppPermission {

    public static boolean isStoragePermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED &&
                    activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission", "Permission is granted");
                return true;
            } else {

                Log.e("Permission", "Permission is revoked");
                activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 1111);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.e("Permission", "Permission is granted");
            return true;
        }
    }

    public static boolean isLocationPermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED &&
                    activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission", "Permission is granted");
                return true;
            } else {

                Log.e("Permission", "Permission is revoked");
                activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, 1112);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.e("Permission", "Permission is granted");
            return true;
        }
    }

    public static boolean isCameraWithStoragePermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED &&
                    activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED &&
                    activity.checkSelfPermission(Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission", "Permission is granted");
                return true;
            } else {

                Log.e("Permission", "Permission is revoked");
                activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}, 1113);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.e("Permission", "Permission is granted");
            return true;
        }
    }

}

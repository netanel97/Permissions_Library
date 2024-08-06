package com.example.permission_lib;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Collections;
import java.util.List;

public class PermissionManager {

    public static final int REQUEST_CODE = 101;

    public interface PermissionCallback {
        void onPermissionsGranted();

        void onPermissionsDenied(List<String> deniedPermissions);

        void onPermissionsPermanentlyDenied(List<String> permanentlyDeniedPermissions);
    }

    private static final PermissionManager instance = new PermissionManager();
    private Context context;

    public static PermissionManager getInstance() {
        return instance;
    }

    public void init(Context context) {
        this.context = context;
    }

    private PermissionManager() {
    }

    public void checkCameraPermission(PermissionCallback callback) {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, callback);
    }
    public void checkStoragePermission(PermissionCallback callback) {
        Activity activity = (Activity) context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    callback.onPermissionsGranted();
                } else {
                    try {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                        activity.startActivity(intent);
                    } catch (Exception e) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                        intent.setData(Uri.parse("package:" + activity.getPackageName()));
                        activity.startActivity(intent);
                    }
                }
            }
        } else {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, callback);
        }
    }

    public void checkLocationPermission(PermissionCallback callback) {
        Activity activity = (Activity) context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, callback);
            } else if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, callback);
            } else {
                callback.onPermissionsGranted();
            }
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, callback);
        }
    }


    public void checkContactsPermission(PermissionCallback callback) {
        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, callback);
    }

    public void checkAudioPermission(PermissionCallback callback) {
        requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, callback);
    }

    public void checkPhonePermission(PermissionCallback callback) {
        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, callback);
    }

    public void checkSmsPermission(PermissionCallback callback) {
        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, callback);
    }

    public void checkCalendarPermission(PermissionCallback callback) {
        requestPermissions(new String[]{Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR}, callback);
    }

    public void checkSensorsPermission(PermissionCallback callback) {
        requestPermissions(new String[]{Manifest.permission.BODY_SENSORS}, callback);
    }


    private void requestPermissions(String[] permissions, PermissionCallback callback) {
        Activity activity = (Activity) context;
        if (activity == null) {
            callback.onPermissionsDenied(Collections.singletonList("Activity is null"));
            return;
        }

        boolean allGranted = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }

        if (allGranted) {
            callback.onPermissionsGranted();
        } else {
            ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE);
        }
    }

    public void handlePermissionResults(String[] permissions, int[] grantResults, PermissionCallback callback) {
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissions[i])) {
                    showManualPermissionAlert(permissions[i], callback);
                    return;
                } else {
                    callback.onPermissionsDenied(Collections.singletonList(permissions[i]));
                    return;
                }
            }
        }
        callback.onPermissionsGranted();
    }

    private void showManualPermissionAlert(String permission, PermissionCallback callback) {
        Activity activity = (Activity) context;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.CustomAlertDialogTheme);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
        builder.setView(dialogView);

        TextView dialogMessage = dialogView.findViewById(R.id.dialog_message);
        dialogMessage.setText("This permission is required for the app to function properly. Please enable it in settings.");

        builder.setTitle("Permission Required")
                .setCancelable(false)
                .setPositiveButton("Settings", (dialog, which) -> {
                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", activity.getPackageName(), null));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                    callback.onPermissionsPermanentlyDenied(Collections.singletonList(permission));
                    dialog.dismiss();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    callback.onPermissionsDenied(Collections.singletonList(permission));
                    dialog.dismiss();
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Apply the custom background color to buttons after showing the dialog
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(activity, R.color.darkGreen));
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(ContextCompat.getColor(activity, R.color.cream));
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(activity, R.color.darkGreen));
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setBackgroundColor(ContextCompat.getColor(activity, R.color.cream));
    }
}

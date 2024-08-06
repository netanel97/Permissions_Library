package com.example.permissions;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.permission_lib.PermissionManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PermissionManager permissionManager;
    private MaterialTextView textViewResult;
    private MaterialButton buttonCamera;
    private MaterialButton buttonStorage;
    private MaterialButton buttonLocation;
    private MaterialButton button_Foreground_service;
    private MaterialButton buttonContacts;
    private MaterialButton buttonAudio;
    private MaterialButton buttonPhone;
    private MaterialButton buttonSms;
    private MaterialButton buttonCalendar;
    private MaterialButton buttonSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionManager = PermissionManager.getInstance();
        permissionManager.init(this); // Initialize with context
        findViews();
        initViews();
    }

    private void findViews() {
        buttonCamera = findViewById(R.id.button_camera);
        buttonStorage = findViewById(R.id.button_storage);
        buttonLocation = findViewById(R.id.button_location);
        buttonContacts = findViewById(R.id.button_contacts);
        buttonAudio = findViewById(R.id.button_audio);
        buttonPhone = findViewById(R.id.button_phone);
        buttonSms = findViewById(R.id.button_sms);
        buttonCalendar = findViewById(R.id.button_calendar);
        buttonSensors = findViewById(R.id.button_sensors);
        textViewResult = findViewById(R.id.textView_result);
        button_Foreground_service = findViewById(R.id.button_Foreground_service);
    }

    private void initViews() {
        buttonCamera.setOnClickListener(v -> permissionManager.checkCameraPermission(new PermissionManager.PermissionCallback() {
            @Override
            public void onPermissionsGranted() {
                updateResult("Camera Permission Granted");
            }

            @Override
            public void onPermissionsDenied(List<String> deniedPermissions) {
                updateResult("Camera Permission Denied: " + deniedPermissions);
            }

            @Override
            public void onPermissionsPermanentlyDenied(List<String> permanentlyDeniedPermissions) {
                updateResult("Camera Permission Permanently Denied: " + permanentlyDeniedPermissions);
                openAppSettings();
            }
        }));

        button_Foreground_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        buttonStorage.setOnClickListener(v -> permissionManager.checkStoragePermission(new PermissionManager.PermissionCallback() {
            @Override
            public void onPermissionsGranted() {
                updateResult("Storage Permissions Granted");
            }

            @Override
            public void onPermissionsDenied(List<String> deniedPermissions) {
                updateResult("Storage Permissions Denied: " + deniedPermissions);
            }

            @Override
            public void onPermissionsPermanentlyDenied(List<String> permanentlyDeniedPermissions) {
                updateResult("Storage Permissions Permanently Denied: " + permanentlyDeniedPermissions);
                openAppSettings();
            }
        }));

        buttonLocation.setOnClickListener(v -> permissionManager.checkLocationPermission(new PermissionManager.PermissionCallback() {
            @Override
            public void onPermissionsGranted() {
                updateResult("Location Permissions Granted");
            }

            @Override
            public void onPermissionsDenied(List<String> deniedPermissions) {
                updateResult("Location Permissions Denied: " + deniedPermissions);
            }

            @Override
            public void onPermissionsPermanentlyDenied(List<String> permanentlyDeniedPermissions) {
                updateResult("Location Permissions Permanently Denied: " + permanentlyDeniedPermissions);
                openAppSettings();
            }
        }));

        buttonContacts.setOnClickListener(v -> permissionManager.checkContactsPermission(new PermissionManager.PermissionCallback() {
            @Override
            public void onPermissionsGranted() {
                updateResult("Contacts Permission Granted");
            }

            @Override
            public void onPermissionsDenied(List<String> deniedPermissions) {
                updateResult("Contacts Permission Denied: " + deniedPermissions);
            }

            @Override
            public void onPermissionsPermanentlyDenied(List<String> permanentlyDeniedPermissions) {
                updateResult("Contacts Permission Permanently Denied: " + permanentlyDeniedPermissions);
                openAppSettings();
            }
        }));

        buttonAudio.setOnClickListener(v -> permissionManager.checkAudioPermission(new PermissionManager.PermissionCallback() {
            @Override
            public void onPermissionsGranted() {
                updateResult("Audio Permission Granted");
            }

            @Override
            public void onPermissionsDenied(List<String> deniedPermissions) {
                updateResult("Audio Permission Denied: " + deniedPermissions);
            }

            @Override
            public void onPermissionsPermanentlyDenied(List<String> permanentlyDeniedPermissions) {
                updateResult("Audio Permission Permanently Denied: " + permanentlyDeniedPermissions);
                openAppSettings();
            }
        }));

        buttonPhone.setOnClickListener(v -> permissionManager.checkPhonePermission(new PermissionManager.PermissionCallback() {
            @Override
            public void onPermissionsGranted() {
                updateResult("Phone Permission Granted");
            }

            @Override
            public void onPermissionsDenied(List<String> deniedPermissions) {
                updateResult("Phone Permission Denied: " + deniedPermissions);
            }

            @Override
            public void onPermissionsPermanentlyDenied(List<String> permanentlyDeniedPermissions) {
                updateResult("Phone Permission Permanently Denied: " + permanentlyDeniedPermissions);
                openAppSettings();
            }
        }));

        buttonSms.setOnClickListener(v -> permissionManager.checkSmsPermission(new PermissionManager.PermissionCallback() {
            @Override
            public void onPermissionsGranted() {
                updateResult("SMS Permission Granted");
            }

            @Override
            public void onPermissionsDenied(List<String> deniedPermissions) {
                updateResult("SMS Permission Denied: " + deniedPermissions);
            }

            @Override
            public void onPermissionsPermanentlyDenied(List<String> permanentlyDeniedPermissions) {
                updateResult("SMS Permission Permanently Denied: " + permanentlyDeniedPermissions);
                openAppSettings();
            }
        }));

        buttonCalendar.setOnClickListener(v -> permissionManager.checkCalendarPermission(new PermissionManager.PermissionCallback() {
            @Override
            public void onPermissionsGranted() {
                updateResult("Calendar Permission Granted");
            }

            @Override
            public void onPermissionsDenied(List<String> deniedPermissions) {
                updateResult("Calendar Permission Denied: " + deniedPermissions);
            }

            @Override
            public void onPermissionsPermanentlyDenied(List<String> permanentlyDeniedPermissions) {
                updateResult("Calendar Permission Permanently Denied: " + permanentlyDeniedPermissions);
                openAppSettings();
            }
        }));

        buttonSensors.setOnClickListener(v -> permissionManager.checkSensorsPermission(new PermissionManager.PermissionCallback() {
            @Override
            public void onPermissionsGranted() {
                updateResult("Sensors Permission Granted");
            }

            @Override
            public void onPermissionsDenied(List<String> deniedPermissions) {
                updateResult("Sensors Permission Denied: " + deniedPermissions);
            }

            @Override
            public void onPermissionsPermanentlyDenied(List<String> permanentlyDeniedPermissions) {
                updateResult("Sensors Permission Permanently Denied: " + permanentlyDeniedPermissions);
                openAppSettings();
            }
        }));

        buttonCamera.setOnClickListener(v -> permissionManager.checkCameraPermission(new PermissionManager.PermissionCallback() {
            @Override
            public void onPermissionsGranted() {
                updateResult("Camera Permission Granted");
            }

            @Override
            public void onPermissionsDenied(List<String> deniedPermissions) {
                updateResult("Camera Permission Denied: " + deniedPermissions);
            }

            @Override
            public void onPermissionsPermanentlyDenied(List<String> permanentlyDeniedPermissions) {
                updateResult("Camera Permission Permanently Denied: " + permanentlyDeniedPermissions);
                openAppSettings();
            }
        }));

    }


    private void updateResult(String result) {
        textViewResult.setText(result);
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();

    }

    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionManager.REQUEST_CODE) {
            permissionManager.handlePermissionResults(permissions, grantResults, new PermissionManager.PermissionCallback() {
                @Override
                public void onPermissionsGranted() {
                    updateResult("Camera Permission Granted");
                }

                @Override
                public void onPermissionsDenied(List<String> deniedPermissions) {
                    updateResult("Camera Permission Denied: " + deniedPermissions);
                }

                @Override
                public void onPermissionsPermanentlyDenied(List<String> permanentlyDeniedPermissions) {
                    updateResult("Camera Permission Permanently Denied: " + permanentlyDeniedPermissions);
                    openAppSettings();
                }
            });
        }
    }
}

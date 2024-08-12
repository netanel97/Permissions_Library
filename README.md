# PermissionManager Library

PermissionManager is an Android library that helps manage permissions in a simple and user-friendly way. It abstracts the complexity of dealing with runtime permissions in Android, providing an easy-to-use API for requesting and handling permissions.

## Features

- Easy API to request permissions
- Handles permission results in a streamlined manner
- Provides utility methods for common permission scenarios
- Supports multiple permissions in a single request

## Installation

Add the following dependency to your `build.gradle` file:

```gradle
dependencies {
    implementation 'com.example.permissionmanager:permissionmanager:1.0.0'
}
```

## Usage

### Requesting Permissions

To request permissions, simply call the `requestPermissions` method:

```java
PermissionManager.requestPermissions(this, new String[]{
    Manifest.permission.CAMERA,
    Manifest.permission.READ_EXTERNAL_STORAGE
}, new PermissionManager.PermissionCallback() {
    @Override
    public void onPermissionGranted() {
        // Permissions granted
    }

    @Override
    public void onPermissionDenied(List<String> deniedPermissions) {
        // Permissions denied
    }
});
```

### Checking Permissions

You can also check if a specific permission is granted:

```java
if (PermissionManager.isPermissionGranted(this, Manifest.permission.CAMERA)) {
    // Permission is granted
} else {
    // Permission is not granted
}
```

## License

PermissionManager is licensed under the MIT License. See [LICENSE](LICENSE) for more information.

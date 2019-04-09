# Permission-Check-In-KOTLIN
Android Permission Check With Kotlin
Welcome to the Permission Check In KOTLIN wiki!
***
# Demo

![Demo](https://github.com/Muhaiminur/Permission-Check-In-KOTLIN/blob/master/DOCUMENTATION/PERMISSION%20KOTLIN.gif)
***
### 1)Add Permission To `AndroidMainifest.xml`class
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
### 2)Define Array in Activity/Fragment
    var PERMISSIONS = arrayOf(
        android.Manifest.permission.INTERNET,
        android.Manifest.permission.ACCESS_NETWORK_STATE,
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )
### 3)Checking Permission Granted or not
    fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
        if (context != null && permissions != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }
### 4)If not granted then asked user
    // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Log.d("Permission", "have description")
            } else {
                // No explanation needed, we can request the permission.
                Log.d("Permission", "no description")
                ActivityCompat.requestPermissions(
                    this, PERMISSIONS,
                    MY_PERMISSIONS_REQUEST
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    check_permission.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                    Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show()
                    var r = result_permission.text.toString()
                    for (c in grantResults.indices) {
                        r = r + PERMISSIONS[c] + " , "
                        Log.d("size", "" + c)
                    }
                    result_permission.setText("granted" + r)
                    Log.d("size", "" + grantResults.size)
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Not Granted", Toast.LENGTH_SHORT).show()
                    var r = result_permission.text.toString()
                    for (c in grantResults) {
                        r = r + PERMISSIONS[c] + " , "
                    }
                    result_permission.setText("Not granted" + r)
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

***
For Download APK [HERE](https://github.com/Muhaiminur/Permission-Check-In-KOTLIN/blob/master/DOCUMENTATION/app-debug.apk)

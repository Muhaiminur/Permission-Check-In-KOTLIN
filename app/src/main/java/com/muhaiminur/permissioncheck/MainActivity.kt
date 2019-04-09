package com.muhaiminur.permissioncheck

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var PERMISSIONS = arrayOf(
        android.Manifest.permission.INTERNET,
        android.Manifest.permission.ACCESS_NETWORK_STATE,
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    private val MY_PERMISSIONS_REQUEST = 777
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        give_permission.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        give_permission.visibility = View.GONE
        check_permission.setOnClickListener {
            if (!hasPermissions(this, PERMISSIONS)) {
                // Permission is not granted
                Toast.makeText(this@MainActivity, "No permission", Toast.LENGTH_SHORT).show()
                give_permission.isClickable = true
                give_permission.visibility = View.VISIBLE


            } else {
                Toast.makeText(this@MainActivity, "Yes permission", Toast.LENGTH_SHORT).show()
                give_permission.isClickable = false
            }

        }
        give_permission.setOnClickListener {
            Toast.makeText(this, "Giving Permission", Toast.LENGTH_SHORT).show()

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
        }
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
}

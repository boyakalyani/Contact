package com.example.contact

//import android.content.Intent
//import android.net.Uri
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.Button
//import android.widget.EditText
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var edittext: EditText
//    private lateinit var button: Button
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        button = findViewById(R.id.button)
//        edittext = findViewById(R.id.editText)
//
//        // Attach set on click listener to the button for initiating intent
//        button.setOnClickListener(View.OnClickListener {
//            // getting phone number from edit text and changing it to String
//            val phone_number = edittext.text.toString()
//
//            // Getting instance of Intent with action as ACTION_CALL
//            var phone_intent = Intent(Intent.ACTION_CALL)
//
//            // Set data of Intent through Uri by parsing phone number
//            phone_intent.data = Uri.parse("tel:$phone_number")
//
//            // start Intent
//            startActivity(phone_intent)
//        })
//    }
//}















import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var edittext: EditText
    private lateinit var button: Button
    private val CALL_PHONE_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        edittext = findViewById(R.id.editText)

        // Attach set on click listener to the button for initiating intent
        button.setOnClickListener(View.OnClickListener {
            // getting phone number from edit text and changing it to String
            val phone_number = edittext.text.toString()

            // check if the CALL_PHONE permission is granted
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // request the permission if it's not granted
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), CALL_PHONE_REQUEST_CODE)
            } else {
                // permission is already granted, initiate the phone call intent
                initiatePhoneCall(phone_number)
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // check if the permission request code matches the CALL_PHONE_REQUEST_CODE
        if (requestCode == CALL_PHONE_REQUEST_CODE) {
            // check if the permission is granted
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission is granted, initiate the phone call intent
                val phone_number = edittext.text.toString()
                initiatePhoneCall(phone_number)
            }
        }
    }

    private fun initiatePhoneCall(phone_number: String) {
        // Getting instance of Intent with action as ACTION_CALL
        var phone_intent = Intent(Intent.ACTION_CALL)

        // Set data of Intent through Uri by parsing phone number
        phone_intent.data = Uri.parse("tel:$phone_number")

        // start Intent
        startActivity(phone_intent)
    }
}

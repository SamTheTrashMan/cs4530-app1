package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private var first: String? = null
    private var last: String? = null
    private var loginView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //receive bundle
        val receivedIntent = intent
        first = receivedIntent.getStringExtra("FIRST")
        last = receivedIntent.getStringExtra("LAST")
        if (first.isNullOrBlank() || last.isNullOrBlank()) {
            Toast.makeText(this@LoginActivity, "Empty string received", Toast.LENGTH_SHORT).show()
        } else {
            loginView = findViewById(R.id.TextViewLogin)
            loginView!!.text = first + " " + last + " is logged in!"
        }
    }
}
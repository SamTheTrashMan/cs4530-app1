package com.example.app1

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //ui
    private var submitButton: Button? = null
    private var picButton: Button? = null
    private var firstEt: EditText? = null
    private var middleEt: EditText? = null
    private var lastEt: EditText? = null
    private var ivProfile: ImageView? = null
    //vars
    private var first: String? = null
    private var middle: String? = null
    private var last: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton = findViewById(R.id.buttonSubmit)
        picButton = findViewById(R.id.buttonPic)

        submitButton!!.setOnClickListener(this)
        picButton!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonSubmit -> {

                //Get the string from the EditText
                firstEt = findViewById(R.id.editTextFirst)
                middleEt = findViewById(R.id.editTextMiddle)
                lastEt = findViewById(R.id.editTextLast)

                first = firstEt!!.text.toString()
                middle = middleEt!!.text.toString()
                last = lastEt!!.text.toString()

                // check if first or last is blank/null
                if (first.isNullOrBlank() || last.isNullOrBlank()) {
                    Toast.makeText(this@MainActivity, "Please Enter Your First and Last Name!", Toast.LENGTH_SHORT).show()
                } else {
                    // go to login page
                    val messageIntent = Intent(this, LoginActivity::class.java)
                    val messageBundle = Bundle()
                    messageBundle.putString("FIRST", first)
                    messageBundle.putString("LAST", last)
                    messageIntent.putExtras(messageBundle)
                    this.startActivity(messageIntent)
                }
            }
            R.id.buttonPic -> {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                cameraActivity.launch(cameraIntent)
            }
        }
    }
    private val cameraActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == RESULT_OK)
        {
            ivProfile = findViewById(R.id.imageViewProfile)

            if(Build.VERSION.SDK_INT >= 33)
            {
                val profilePic = result.data!!.getParcelableExtra("data", Bitmap::class.java)
                ivProfile!!.setImageBitmap(profilePic)
            }
            else
            {
                val profilePic = result.data!!.getParcelableExtra<Bitmap>("data")
                ivProfile!!.setImageBitmap(profilePic)
            }
        }
    }
}
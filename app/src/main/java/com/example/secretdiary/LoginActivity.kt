package com.example.secretdiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var textview1=findViewById<TextView>(R.id.tvTitle)
        var edittext1=findViewById<EditText>(R.id.etPin)
        var button1=findViewById<Button>(R.id.btnLogin)

        button1.setOnClickListener {
            if(edittext1.text.toString()=="1234"){
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            else{
                edittext1.error="Wrong PIN!"
            }
        }

    }
}
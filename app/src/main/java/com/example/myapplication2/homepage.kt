package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class homepage : AppCompatActivity() {
    private val chmura = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val button: Button = findViewById(R.id.profile)
        button.setOnClickListener {
            // Tworzenie intentu, który przechodzi do aktywności profilu
            val intent = Intent(this, profil::class.java)
            startActivity(intent)
        }
    }
}


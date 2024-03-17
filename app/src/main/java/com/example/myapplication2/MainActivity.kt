package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var authenticator: FirebaseAuth
    private val LOG_DEBUG = "LOG_DEBUG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        authenticator = FirebaseAuth.getInstance()
    }

    fun wyslij(view: View) {
        val poleMail: EditText = findViewById(R.id.mail)
        val poleHaslo: EditText = findViewById(R.id.haslo)
        val mail: String = poleMail.text.toString()
        val haslo: String = poleHaslo.text.toString()

        if(mail.isNotEmpty() && haslo.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, haslo)
                .addOnSuccessListener {
                    val intent = Intent(this, homepage::class.java).apply {
                        flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    }
                    startActivity(intent)
                }
                .addOnFailureListener { exc ->
                    Log.d(LOG_DEBUG, exc.message.toString())
                }
        }
    }
}

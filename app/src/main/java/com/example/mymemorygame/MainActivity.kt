package com.example.mymemorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.setPadding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlayGame: Button = findViewById(R.id.btnPlayGame)

        btnPlayGame.setOnClickListener {
            gameInit()
        }
    }

    private fun gameInit(){
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }
}
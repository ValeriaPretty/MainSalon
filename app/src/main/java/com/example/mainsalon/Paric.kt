package com.example.mainsalon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.button.MaterialButton


private lateinit var yourButton: MaterialButton

class Paric : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paric)
        yourButton = findViewById(R.id.buttonnn)
        nice()
    }




    fun nice(){
        yourButton.setOnClickListener {
            startNewActivity()
        }
    }

    fun startNewActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
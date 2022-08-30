package com.anara.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var haloTextView: TextView
    private var isActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        haloTextView = findViewById(R.id.haloTextView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener { toggleHalo() }
    }

    private fun toggleHalo() {
        isActive = !isActive
        haloTextView.text = if (isActive) "Halo Dunia!" else "Hello World!"
    }
}
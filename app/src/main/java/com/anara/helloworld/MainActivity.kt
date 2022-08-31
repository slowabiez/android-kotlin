package com.anara.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var haloTextView: TextView
    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initContent()
    }

    private fun initContent() {
        initTextView()
        initButton()
    }

    private fun initTextView() {
        haloTextView = findViewById(R.id.haloTextView)
        haloTextView.text = number.toString()
    }

    private fun initButton() {
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener { increase() }

        val resetButton: Button = findViewById(R.id.resetButton)
        resetButton.setOnClickListener { reset() }
    }

    private fun increase() {
        number += 1
        haloTextView.text = "$number"
    }

    private fun reset() {
        number = 0
        haloTextView.text = "$number"
    }
}
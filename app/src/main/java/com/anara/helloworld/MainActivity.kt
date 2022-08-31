package com.anara.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var etBerat: EditText
    private lateinit var etTinggi: EditText
    private lateinit var tvHasil: TextView
    private lateinit var tvKategori: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etBerat = findViewById(R.id.etBerat)
        this.etTinggi = findViewById(R.id.etTinggi)

        this.tvHasil = findViewById(R.id.tvHasil)
        this.tvKategori = findViewById(R.id.tvKategori)

    }

}
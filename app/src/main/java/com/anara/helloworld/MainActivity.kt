package com.anara.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anara.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        this.binding.btnHitung.setOnClickListener { this.hitung() }
    }

    private fun hitung() {
        val berat = this.binding.etBerat.text.toString().toFloat()
        val tinggi = this.binding.etTinggi.text.toString().toFloat() / 100
        val bmi = berat / (tinggi * tinggi)

        val selectedId = this.binding.rgKelamin.checkedRadioButtonId
        val isMale = selectedId == R.id.rbPria
        val kategori = this.getKategori(bmi, isMale)

        this.binding.tvBmi.text = getString(R.string.bmi_x, bmi)
        this.binding.tvKategori.text = getString(R.string.kategori_x, kategori)
    }

    private fun getKategori(bmi: Float, isMale: Boolean): String {
        val stringRes = if (isMale) {
            when {
                bmi < 20.5 -> R.string.kurus
                bmi >= 27.0 -> R.string.gemuk
                else -> R.string.ideal
            }
        } else {
            when {
                bmi < 18.5 -> R.string.kurus
                bmi >= 25.0 -> R.string.gemuk
                else -> R.string.ideal
            }
        }
        return getString(stringRes)
    }

}
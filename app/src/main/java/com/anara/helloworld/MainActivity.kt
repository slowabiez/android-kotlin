package com.anara.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.anara.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        this.binding.etQty.setText("1")

        this.binding.btnMin.setOnClickListener { this.updateQty(-1) }
        this.binding.btnPlus.setOnClickListener { this.updateQty(1) }

        this.binding.btnOrder.setOnClickListener { this.placeOrder() }
    }

    private fun updateQty(amount: Int) {
        val qty = this.binding.etQty.text.toString()
        var total = amount
        if (qty.isNotEmpty()) total += qty.toInt()
        if (total < 1) total = 1
        this.binding.etQty.setText("$total")
    }

    private fun placeOrder() {
        val name = this.binding.etName.text
        if (name.isEmpty()) return Toast.makeText(
            this,
            getString(R.string.name_required),
            Toast.LENGTH_SHORT
        ).show()

        val qty = this.binding.etQty.text.toString()
        if (qty.isEmpty()) return Toast.makeText(
            this,
            getString(R.string.qty_required),
            Toast.LENGTH_SHORT
        ).show()

        var price = 10000
        val cream = 5000
        val chocolate = 7500

        if (this.binding.cbCream.isChecked) price += cream
        if (this.binding.cbChocolate.isChecked) price += chocolate

        price *= qty.toInt()

        val result = getString(R.string.result, name, price)

        this.binding.tvResult.text = result
    }
}
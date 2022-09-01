package com.anara.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.anara.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        val adapter = MainAdapter()

        this.binding.recyclerView.adapter = adapter
        this.binding.recyclerView.setHasFixedSize(true)

        viewModel.getData().observe(this) {
            adapter.updateData(it)
        }

        viewModel.getStatus().observe(this) {
            this.updateUI(it)
        }
    }

    private fun updateUI(status: ApiStatus) {
        this.binding.progressBar.visibility = if (status == ApiStatus.LOADING) View.VISIBLE else View.GONE
        this.binding.recyclerView.visibility = if (status == ApiStatus.SUCCESS) View.VISIBLE else View.GONE
        this.binding.errorTextView.visibility = if (status == ApiStatus.FAILED) View.VISIBLE else View.GONE
    }

}
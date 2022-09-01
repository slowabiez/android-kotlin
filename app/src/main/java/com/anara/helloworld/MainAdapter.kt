package com.anara.helloworld

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anara.helloworld.databinding.ListItemBinding

class MainAdapter(private val data: List<Hewan>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(hewan: Hewan, position: Int, total: Int) = with(binding) {
            imageView.setImageResource(hewan.gambarResId)
            nameTextView.text = hewan.nama
            latinTextView.text = hewan.namaLatin
            if (position == total - 1) divider.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.data[position], position, this.data.size)
    }

    override fun getItemCount() = this.data.size
}
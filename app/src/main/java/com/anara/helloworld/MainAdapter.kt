package com.anara.helloworld

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.anara.helloworld.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val data = ArrayList<Hewan>()

    class ViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(hewan: Hewan, position: Int, total: Int) = with(binding) {
            Picasso.get().load(HewanApi.getHewanUrl(hewan.imageId)).into(this.imageView)
            this.nameTextView.text = hewan.nama
            this.latinTextView.text = hewan.namaLatin
            this.divider.visibility = if (position == total - 1) View.GONE else View.VISIBLE

            this.root.setOnClickListener {
                val message = this.root.context.getString(R.string.x_diklik, hewan.nama)
                Toast.makeText(this.root.context, message, Toast.LENGTH_SHORT).show()

            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<Hewan>) {
        this.data.clear()
        data.addAll(newData)
        this.notifyDataSetChanged()
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
package com.anara.helloworld

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anara.helloworld.databinding.ListItemBinding
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val data = ArrayList<Covid>()

    class ViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        val formater = NumberFormat.getInstance(Locale("ID", "id"))

        fun bind(covid: Covid, position: Int, total: Int) = with(binding) {
            val simpleDateFormat = SimpleDateFormat("d MMMM yyyy", Locale("ID", "id"))
            this.dateTextView.text = simpleDateFormat.format(covid.key)
            this.amountTextView.text = this.root.context.getString(R.string.x_orang, formater.format(covid.jumlah_positif.value))
            this.divider.visibility = if (position < total - 1) View.VISIBLE else View.GONE
        }

    }

    fun getDate(position: Int): Date {
        return Date(this.data[position].key)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<Covid>) {
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
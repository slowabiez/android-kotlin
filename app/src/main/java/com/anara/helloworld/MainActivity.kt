package com.anara.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.anara.helloworld.databinding.ActivityMainBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MainAdapter()
        this.binding.recyclerView.adapter = adapter
        this.binding.recyclerView.setHasFixedSize(true)

        val simpleDateFormat = SimpleDateFormat("d MMM yy", Locale("ID", "id"))
        with(this.binding.lineChart) {
            description.text = ""
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val index = value.toInt()
                    val inBounds = index > -1 && index < adapter.itemCount
                    return if (inBounds) simpleDateFormat.format(adapter.getDate(index)) else "-"
                }
            }
            axisRight.isEnabled = false
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.setDrawInside(false)

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    val index = (h?.x?.toInt() ?: 0)
                    Log.d("CHECK", "INDEX: $index")
                    binding.recyclerView.scrollToPosition(index)
                }

                override fun onNothingSelected() {
                }
            })
        }

        this.viewModel.getData().observe(this) {
            adapter.updateData(it)
            this.updateChart(it)
        }

        this.viewModel.getStatus().observe(this) {
            this.updateUI(it)
        }
    }

    private fun updateChart(data: List<Covid>) {
        val entries = ArrayList<Entry>()
        var index = 0f
        for (covid in data) entries.add(Entry(index++, covid.jumlah_positif.value.toFloat()))

        val dataSet = LineDataSet(entries, "Jumlah kasus positif per-hari")
        dataSet.color = ContextCompat.getColor(this, R.color.purple_500)
        dataSet.fillColor = dataSet.color
        dataSet.setDrawFilled(true)
        dataSet.setDrawCircles(false)

        this.binding.lineChart.data = LineData(dataSet)
        this.binding.lineChart.invalidate()
    }

    private fun updateUI(status: ApiStatus) {
        this.binding.progressBar.visibility = if (status == ApiStatus.LOADING) View.VISIBLE else View.GONE
        this.binding.lineChart.visibility = if (status == ApiStatus.SUCCESS) View.VISIBLE else View.GONE
        this.binding.dividerChart.visibility = if (status == ApiStatus.SUCCESS) View.VISIBLE else View.GONE
        this.binding.recyclerView.visibility = if (status == ApiStatus.SUCCESS) View.VISIBLE else View.GONE
        this.binding.errorTextView.visibility = if (status == ApiStatus.FAILED) View.VISIBLE else View.GONE
    }

}
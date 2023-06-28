package com.marcod.malabag.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcod.malabag.R
import com.marcod.malabag.model.Bags

class BagsAdapter(private var data: List<Bags>,
                 private val listener: (Bags) -> Unit)
    : RecyclerView.Adapter<BagsAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflaterView = layoutInflater.inflate(R.layout.row_item_bag, parent, false)
        return ViewHolder(inflaterView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BagsAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvSize:TextView = view.findViewById(R.id.tv_size)
        private val tvColors:TextView = view.findViewById(R.id.tv_colors)

        fun bindItem(data:Bags, listener: (Bags) -> Unit, context: Context){
            tvSize.setText(data.size)
            tvColors.setText(data.colors)

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }
}

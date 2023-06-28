package com.marcod.malabag.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcod.malabag.R
import com.marcod.malabag.model.Bag

class ProductMenAdapter(private var data: List<Bag>,
                        private val listener: (Bag) -> Unit)
    : RecyclerView.Adapter<ProductMenAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductMenAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflaterView = layoutInflater.inflate(R.layout.row_item_men, parent, false)
        return ViewHolder(inflaterView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProductMenAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle:TextView = view.findViewById(R.id.tv_bagname)
        private val tvPrice:TextView = view.findViewById(R.id.tv_price)

        private val tvImage: ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data:Bag, listener: (Bag) -> Unit, context: Context){
            tvTitle.setText(data.name)
            tvPrice.setText(data.price)

            Glide.with(context)
                .load(data.image)
                .into(tvImage)

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }
}

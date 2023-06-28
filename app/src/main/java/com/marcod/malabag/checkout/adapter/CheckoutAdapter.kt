package com.marcod.malabag.checkout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcod.malabag.R
import com.marcod.malabag.checkout.model.Checkout
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var data: List<Checkout>,
                      private val listener: (Checkout) -> Unit)
    : RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflaterView = layoutInflater.inflate(R.layout.row_item_checkout, parent, false)
        return ViewHolder(inflaterView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CheckoutAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle:TextView = view.findViewById(R.id.tv_bagname)
        private val tvPrice:TextView = view.findViewById(R.id.tv_price)

        private val tvImage: ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data:Checkout, listener: (Checkout) -> Unit, context: Context){

            val localID = Locale("id", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)
            tvPrice.setText(formatRupiah.format(data.harga!!.toDouble()))

            if (data.name!!.startsWith("Total")){
                tvTitle.setText(data.name)
                tvTitle.setCompoundDrawables(null, null, null, null)
            } else {
                tvTitle.setText(""+data.name)
            }

            Glide.with(context)
                .load(data.image)
                .into(tvImage)

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }
}

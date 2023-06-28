package com.marcod.malabag.home.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.marcod.malabag.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.marcod.malabag.checkout.CheckoutActivity
import com.marcod.malabag.model.Bag
import com.marcod.malabag.utils.Preferences
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class DashboardFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Bag>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Bag")

        tv_nama.setText(preferences.getValues("nama"))
        if (preferences.getValues("saldo").equals("")){
            currency(preferences.getValues("saldo")!!.toDouble(), tv_saldo)
        }

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        rv_product.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_men.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        getData()
    }

    private fun getData(){
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }
            override fun onDataChange(dataSnapShot: DataSnapshot) {
                dataList.clear()
                for (getdataSnapshot in dataSnapShot.children) {
                    var bags = getdataSnapshot.getValue(Bag::class.java)
                    dataList.add(bags!!)
                }

                rv_product.adapter = ProductAdapter(dataList){
                    var intent = Intent(context, CheckoutActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
                rv_men.adapter = ProductMenAdapter(dataList){
                    var intent = Intent(context, CheckoutActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
            }
        })
    }

    private fun currency(harga : Double, textview: TextView) {
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textview.setText(format.format(harga))
    }
}
package com.marcod.malabag.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.marcod.malabag.R
import com.marcod.malabag.model.Bags
import kotlinx.android.synthetic.main.activity_checkout.*

class Checkout2Activity : AppCompatActivity() {

    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Bags>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout2)

        getData()

        tv_pay.setOnClickListener {
            var intent = Intent(this@Checkout2Activity, SuccessCheckoutActivity::class.java).putExtra("data", dataList)
            startActivity(intent)
        }
    }
    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@Checkout2Activity, ""+p0.message, Toast.LENGTH_LONG).show()
            }
            override fun onDataChange(p0: DataSnapshot) {
                dataList.clear()

                for (getdataSnapshot in p0.children){
                    val Bag = getdataSnapshot.getValue(Bags::class.java)
                    dataList.add(Bag!!)
                }
            }
        })
    }
}
package com.marcod.malabag.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.marcod.malabag.R
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.marcod.malabag.home.CartFragment
import com.marcod.malabag.home.HomeActivity
import com.marcod.malabag.model.Bag
import com.marcod.malabag.model.Bags
import kotlinx.android.synthetic.main.activity_checkout.btn_cancel
import kotlinx.android.synthetic.main.activity_checkout.img_bag
import kotlinx.android.synthetic.main.activity_checkout.tv_bagname
import kotlinx.android.synthetic.main.activity_checkout.tv_bagprice
import kotlinx.android.synthetic.main.activity_checkout.tv_colors
import kotlinx.android.synthetic.main.activity_checkout.tv_pay
import kotlinx.android.synthetic.main.activity_checkout.tv_size


class CheckoutActivity : AppCompatActivity() {

    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Bags>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        val data = intent.getParcelableExtra<Bag>("data")

        val ButtonOpen : ImageView = findViewById(R.id.ic_fav)
        ButtonOpen.setOnClickListener {
            val cartFragment = CartFragment
            val fragment : Fragment? =
            supportFragmentManager.findFragmentByTag(CartFragment::class.java.simpleName)
            if (fragment !is CartFragment){
                supportFragmentManager.beginTransaction()
            }
        }

        mDatabase = FirebaseDatabase.getInstance().getReference("Bag")
            .child(data!!.name.toString())
            .child(data.price.toString())

        tv_bagname.text = data.name
        tv_bagprice.text = data.price
        tv_colors.text = data.colors
        tv_size.text = data.size



        Glide.with(this)
            .load(data.image)
            .into(img_bag)

        getData()

        btn_cancel.setOnClickListener{
            val goHome = Intent(this@CheckoutActivity, HomeActivity::class.java)
            startActivity(goHome)
        }


        tv_pay.setOnClickListener {
            val intent = Intent(this@CheckoutActivity, SuccessCheckoutActivity::class.java).putExtra("data", dataList)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@CheckoutActivity, ""+p0.message, Toast.LENGTH_LONG).show()
            }
            override fun onDataChange(p0: DataSnapshot) {
                dataList.clear()

                for (dataSnapshot in p0.children){
                    val bag = dataSnapshot.getValue(Bags::class.java)
                    dataList.add(bag!!)
                }
            }
        })
    }
}
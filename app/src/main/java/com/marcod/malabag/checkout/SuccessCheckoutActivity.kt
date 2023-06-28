package com.marcod.malabag.checkout


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.marcod.malabag.DetailBagActivity
import com.marcod.malabag.R
import kotlinx.android.synthetic.main.activity_success_checkout.tv_pay

class SuccessCheckoutActivity : AppCompatActivity() {

    private lateinit var imgbag : ImageView
    private lateinit var tvbagname : TextView
    private lateinit var tvbagprice : TextView
    private lateinit var tvsize : TextView
    private lateinit var tvcolors : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_checkout)


        tv_pay.setOnClickListener{
            var goSuccess = Intent(this@SuccessCheckoutActivity, DetailBagActivity::class.java)
            startActivity(goSuccess)
        }


    }
}
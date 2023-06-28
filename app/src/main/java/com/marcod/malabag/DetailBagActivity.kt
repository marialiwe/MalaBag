package com.marcod.malabag

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marcod.malabag.home.HomeActivity
import kotlinx.android.synthetic.main.activity_detail_bag.*

class DetailBagActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_bag)

        btn_home.setOnClickListener{
            var goHome = Intent(this@DetailBagActivity, HomeActivity::class.java)
            startActivity(goHome)
        }


    }
}
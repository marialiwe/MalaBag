package com.marcod.malabag.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marcod.malabag.R
import com.marcod.malabag.home.HomeActivity
import kotlinx.android.synthetic.main.activity_my_wallet_success.*

class MyWalletSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet_success)

        btn_home.setOnClickListener {
            finishAffinity()

            val intent = Intent(
                this@MyWalletSuccessActivity,
                HomeActivity::class.java
            )
            startActivity(intent)

        }
    }
}
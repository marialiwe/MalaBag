package com.marcod.malabag.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcod.malabag.R
import com.marcod.malabag.wallet.adapter.WalletAdapter
import com.marcod.malabag.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_my_wallet.*

class MyWalletActivity : AppCompatActivity() {

    private var dataList = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        loadDummyData()
    }
    private fun initListener() {
        rv_transaksi.layoutManager = LinearLayoutManager(this)
        rv_transaksi.adapter = WalletAdapter(dataList){

        }

        btn_top_up.setOnClickListener {
            startActivity(Intent(this, MyWalletTopUpActivity::class.java))
        }
    }

    private fun loadDummyData() {
        dataList.add(
            Wallet(
                "Sling Bag",
                "Selasa 5 Juli, 2022",
                1299000.0,
                "0"
            )
        )
        dataList.add(
            Wallet(
                "Fabric Backpack",
                "Selasa 5 Juli, 2022",
                2049000.0,
                "1"
            )
        )
        dataList.add(
            Wallet(
                "Icon Leather",
                "Senin 4 Juli, 2022",
                1799000.0,
                "0"
            )
        )

        initListener()

    }
}

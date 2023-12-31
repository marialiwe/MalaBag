package com.marcod.malabag.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.marcod.malabag.R
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.*
import java.lang.NumberFormatException

class MyWalletTopUpActivity : AppCompatActivity() {
    private var status10K : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet_top_up)

        initListener()

    }

    private fun initListener() {
        btn_top_up.setOnClickListener {
            startActivity(Intent(this, MyWalletSuccessActivity::class.java))
        }

        tv_10k.setOnClickListener {
            if (status10K) {
                deselectMoney(tv_10k)
            } else {
                selectMoney(tv_10k)
            }
        }

        et_amount.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) { }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {  }

            override fun afterTextChanged(s: Editable) {
                try {
                    if (s.toString().toInt() >= 10000) {
                        btn_top_up.visibility = View.VISIBLE
                    } else {
                        tv_10k.setTextColor(resources.getColor(R.color.black))
                        tv_10k.setBackgroundResource(R.drawable.searchviewbg)
                        status10K = false
                        btn_top_up.visibility = View.INVISIBLE
                    }
                } catch (e : NumberFormatException) {
                    tv_10k.setTextColor(resources.getColor(R.color.black))
                    tv_10k.setBackgroundResource(R.drawable.searchviewbg)
                    status10K = false
                    btn_top_up.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun selectMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.red))
        textView.setBackgroundResource(R.drawable.searchviewbg)
        status10K = true

        btn_top_up.visibility = View.VISIBLE
        et_amount.setText("10000")
    }

    private fun deselectMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.black))
        textView.setBackgroundResource(R.drawable.searchviewbg)
        status10K = false

        btn_top_up.visibility = View.INVISIBLE
        et_amount.setText("")
    }

}

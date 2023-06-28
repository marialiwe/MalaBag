package com.marcod.malabag

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.marcod.malabag.sign.signin.SignInActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity,
                SignInActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}
package com.marcod.malabag.sign.signin

import com.marcod.malabag.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.marcod.malabag.home.HomeActivity
import com.marcod.malabag.sign.signup.SignUpActivity
import com.marcod.malabag.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    lateinit var iUsername:String
    lateinit var iPassword:String

    lateinit var mDatabase : DatabaseReference
    lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        if (preference.getValues("status").equals("1")){
            finishAffinity()

            var goHome = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

        btnsignin.setOnClickListener{
            iUsername = etuser.text.toString()
            iPassword = etpassword.text.toString()

            if (iUsername.equals("")) {
                etuser.error = "Insert your username!"
                etuser.requestFocus()
            } else if (iPassword.equals("")) {
                etpassword.error = "Insert your password!"
                etpassword.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        }
        btn_signup.setOnClickListener{
            var goSignup = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(goSignup)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(
                        this@SignInActivity, "Username not found",
                        Toast.LENGTH_LONG
                    ).show()
                } else {

                    if (user.password.equals(iPassword)) {

                        preference.setValue("nama", user.nama.toString())
                        preference.setValue("user", user.username.toString())
                        preference.setValue("url", user.url.toString())
                        preference.setValue("email", user.email.toString())
                        preference.setValue("saldo", user.saldo.toString())
                        preference.setValue("status", "1")

                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@SignInActivity, "Incorrect Password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@SignInActivity, databaseError.message ,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
package com.marcod.malabag.sign.signup

import com.marcod.malabag.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.marcod.malabag.sign.signin.SignInActivity
import com.marcod.malabag.sign.signin.User
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.btn_signup
import kotlinx.android.synthetic.main.activity_sign_up.etpassword
import kotlinx.android.synthetic.main.activity_sign_up.etuser

class SignUpActivity : AppCompatActivity() {
    lateinit var sUsername: String
    lateinit var sPassword: String
    lateinit var sNama: String
    lateinit var sEmail: String

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseInstance: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        btn_signup.setOnClickListener {

            sUsername = etuser.text.toString()
            sPassword = etpassword.text.toString()
            sNama = etname.text.toString()
            sEmail = etemail.text.toString()

            if (sUsername.equals("")) {
                etuser.error = "Please input your username"
                etuser.requestFocus()
            } else if (sPassword.equals("")) {
                etpassword.error = "Please input your password"
                etpassword.requestFocus()
            } else if (sNama.equals("")) {
                etname.error = "Please input your name"
                etname.requestFocus()
            } else if (sEmail.equals("")) {
                etemail.error = "Please input username"
                etemail.requestFocus()
            } else {
                saveUsername (sUsername, sPassword, sNama, sEmail){

                }
            }
        }
        btn_signin.setOnClickListener{
            var goSignup = Intent(this@SignUpActivity, SignInActivity::class.java)
            startActivity(goSignup)
        }
    }

    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String, function: () -> Unit) {
        var user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword

        if (sUsername != null) {
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(sUsername: String, data: User) {
        mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    mDatabaseReference.child(sUsername).setValue(data)

                    var goSignUpPhotoscreen = Intent(this@SignUpActivity,
                    SignUpPhotoScreenActivity::class.java).putExtra("nama", data.nama)
                    startActivity(goSignUpPhotoscreen)
                } else {
                    Toast.makeText(this@SignUpActivity, "Welcome!", Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}
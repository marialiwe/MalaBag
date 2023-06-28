package com.marcod.malabag.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.marcod.malabag.R
import com.marcod.malabag.sign.signin.SignInActivity
import com.marcod.malabag.utils.Preferences
import com.marcod.malabag.wallet.MyWalletActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(){

    lateinit var preferences: Preferences
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(context!!)

        tvuser.text = preferences.getValues("nama")
        tvemail.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(img_profile)

        tv_my_wallet.setOnClickListener {
            startActivity(Intent(activity, MyWalletActivity::class.java))
        }
    }
    private fun tombolkeluar(){
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val i = Intent(context, SignInActivity::class.java)
        startActivity(i)
        activity?.finish()
    }
}
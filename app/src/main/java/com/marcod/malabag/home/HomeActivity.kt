package com.marcod.malabag.home


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.marcod.malabag.R
import com.marcod.malabag.home.dashboard.DashboardFragment
import com.marcod.malabag.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val  fragmentHome = DashboardFragment()
        val  fragmentFavorite = FavoriteFragment()
        val  fragmentProfile = ProfileFragment()
        val  fragmentCart = CartFragment()

        setFragment(fragmentHome)

        iv_menu1.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(iv_menu1, R.drawable.home)
            changeIcon(iv_menu2, R.drawable.favorite)
            changeIcon(iv_menu3, R.drawable.person)
            changeIcon(iv_menu4, R.drawable.cart)
        }
        iv_menu2.setOnClickListener {
            setFragment(fragmentFavorite)

            changeIcon(iv_menu1, R.drawable.home_nac)
            changeIcon(iv_menu2, R.drawable.favorite_ac)
            changeIcon(iv_menu3, R.drawable.person)
            changeIcon(iv_menu4, R.drawable.cart)
        }
        iv_menu3.setOnClickListener {
            setFragment(fragmentProfile)

            changeIcon(iv_menu1, R.drawable.home_nac)
            changeIcon(iv_menu2, R.drawable.favorite)
            changeIcon(iv_menu3, R.drawable.person_ac)
            changeIcon(iv_menu4, R.drawable.cart)
        }
        iv_menu4.setOnClickListener {
            setFragment(fragmentCart)

            changeIcon(iv_menu1, R.drawable.home_nac)
            changeIcon(iv_menu2, R.drawable.favorite)
            changeIcon(iv_menu3, R.drawable.person)
            changeIcon(iv_menu4, R.drawable.cart_ac)
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int){
        imageView.setImageResource(int)
    }
}
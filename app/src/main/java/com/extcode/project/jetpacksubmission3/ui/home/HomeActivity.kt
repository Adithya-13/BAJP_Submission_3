package com.extcode.project.jetpacksubmission3.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.extcode.project.jetpacksubmission3.R
import com.extcode.project.jetpacksubmission3.databinding.ActivityHomeBinding
import com.extcode.project.jetpacksubmission3.ui.favorite.FavoriteFragment
import com.extcode.project.jetpacksubmission3.ui.movies.MoviesFragment
import com.extcode.project.jetpacksubmission3.ui.tvshows.TvShowsFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationChange(MoviesFragment())

        binding.bottomNavigationContainer.setNavigationChangeListener { _, position ->
            when (position) {
                0 -> navigationChange(MoviesFragment())
                1 -> navigationChange(TvShowsFragment())
                2 -> navigationChange(FavoriteFragment())
            }
        }
    }

    private fun navigationChange(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

}
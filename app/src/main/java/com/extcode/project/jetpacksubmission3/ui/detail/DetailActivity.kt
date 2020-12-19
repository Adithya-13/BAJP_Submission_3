package com.extcode.project.jetpacksubmission3.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.extcode.project.jetpacksubmission3.R
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import com.extcode.project.jetpacksubmission3.databinding.ActivityDetailBinding
import com.extcode.project.jetpacksubmission3.viewmodel.ViewModelFactory
import com.extcode.project.jetpacksubmission3.vo.Resource
import com.extcode.project.jetpacksubmission3.vo.Status

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TYPE = "extraType"
        const val EXTRA_ID = "extraId"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getIntExtra(EXTRA_TYPE, -1)
        val enumType: DetailType = DetailType.values()[type]

        val id = intent.getIntExtra(EXTRA_ID, -1)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        binding.progressBar.visibility = View.VISIBLE
        binding.nestedScrollView.visibility = View.GONE
        when (enumType) {
            DetailType.MOVIE -> {
                viewModel.selectedMovieId(id)
                viewModel.movieDetail.observe(this, { movie ->
                    if (movie != null) {
                        showDetail(movie)
                    }
                })
            }
            DetailType.TV_SHOW -> {
                viewModel.selectedTvShowId(id)
                viewModel.tvShowDetail.observe(this, { tvShow ->
                    if (tvShow != null) {
                        showDetail(tvShow)
                    }
                })
            }
        }

        binding.backButton.setOnClickListener { onBackPressed() }
        binding.share.setOnClickListener { share() }
        binding.favoriteButton.setOnClickListener {
            when (enumType) {
                DetailType.MOVIE -> {
                    viewModel.setFavoriteMovie()
                }
                DetailType.TV_SHOW -> {
                    viewModel.setFavoriteTvShow()
                }
            }
        }
    }

    private fun showDetail(movie: Resource<MovieEntity>) {
        when (movie.status) {
            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
            Status.SUCCESS -> if (movie.data != null) {
                binding.progressBar.visibility = View.GONE
                binding.nestedScrollView.visibility = View.VISIBLE

                val state = movie.data.favorite

                setFavoriteState(state)
                populateDetail(movie.data)
            }
            Status.ERROR -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(
                    this,
                    "Terjadi kesalahan",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            binding.favoriteButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.favoriteButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

    private fun populateDetail(movieEntity: MovieEntity) {
        with(binding) {
            titleDetail.text = movieEntity.title
            date.text = movieEntity.releaseDate
            overview.text = movieEntity.overview
            popularity.text = getString(
                R.string.popularity_detail,
                movieEntity.popularity.toString(),
                movieEntity.voteCount.toString(),
                movieEntity.voteAverage.toString()
            )
            userScore.text = movieEntity.voteAverage.toString()
            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, movieEntity.posterPath))
                .into(posterTopBar)
            posterTopBar.tag = movieEntity.posterPath

            Glide.with(this@DetailActivity)
                .load(getString(R.string.baseUrlImage, movieEntity.posterPath))
                .into(subPoster)
            subPoster.tag = movieEntity.posterPath
        }
    }

    private fun share() {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder.from(this).apply {
            setType(mimeType)
            setChooserTitle(getString(R.string.shareTitle))
            setText(getString(R.string.shareBody))
            startChooser()
        }
    }

}
package com.extcode.project.jetpacksubmission3.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.extcode.project.jetpacksubmission3.R
import com.extcode.project.jetpacksubmission3.data.source.local.enitity.MovieEntity
import com.extcode.project.jetpacksubmission3.databinding.ActivityDetailBinding
import com.extcode.project.jetpacksubmission3.viewmodel.ViewModelFactory
import com.skydoves.transformationlayout.TransformationAppCompatActivity
import com.skydoves.transformationlayout.TransformationCompat.onTransformationStartContainer
import com.skydoves.transformationlayout.onTransformationStartContainer

class DetailActivity : TransformationAppCompatActivity() {

    companion object {
        const val EXTRA_TYPE = "extraType"
        const val EXTRA_ID = "extraId"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getIntExtra(EXTRA_TYPE, -1)
        val enumType: DetailType = DetailType.values()[type]

        val id = intent.getIntExtra(EXTRA_ID, -1)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        binding.progressBar.visibility = View.VISIBLE
        binding.nestedScrollView.visibility = View.GONE
        when (enumType) {
            DetailType.MOVIE -> {
                viewModel.selectedMovieId(id.toString())
                viewModel.getMovieDetail().observe(this, { movie ->
                    binding.progressBar.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.VISIBLE
                    populateDetail(movie)
                })
            }
            DetailType.TV_SHOW -> {
                viewModel.selectedTvShowId(id.toString())
                viewModel.getTvShowDetail().observe(this, { tvShow ->
                    binding.progressBar.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.VISIBLE
                    populateDetail(tvShow)
                })
            }
        }

        binding.backButton.setOnClickListener { onBackPressed() }
        binding.share.setOnClickListener { share() }
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
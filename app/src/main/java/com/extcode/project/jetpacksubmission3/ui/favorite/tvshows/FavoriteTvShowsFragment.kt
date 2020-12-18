package com.extcode.project.jetpacksubmission3.ui.favorite.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.extcode.project.jetpacksubmission3.R
import com.extcode.project.jetpacksubmission3.databinding.FragmentFavoriteTvShowsBinding
import com.extcode.project.jetpacksubmission3.ui.favorite.FavoriteViewModel
import com.extcode.project.jetpacksubmission3.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FavoriteTvShowsFragment : Fragment() {

    private var _fragmentFavoriteTvShowsBinding: FragmentFavoriteTvShowsBinding? = null
    private val binding get() = _fragmentFavoriteTvShowsBinding!!

    private lateinit var tvShowsAdapter: FavoriteTvShowsAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentFavoriteTvShowsBinding =
            FragmentFavoriteTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding.rvBookmarkTvShows)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        tvShowsAdapter = FavoriteTvShowsAdapter()

        binding.progressBar.visibility = View.VISIBLE
        binding.notFound.visibility = View.GONE
        viewModel.getBookmarkedTvShows().observe(this, { tvShows ->
            if (tvShows.isNotEmpty()) {
                binding.progressBar.visibility = View.GONE
                binding.notFound.visibility = View.GONE
                tvShowsAdapter.submitList(tvShows)
                tvShowsAdapter.notifyDataSetChanged()
            } else {
                binding.progressBar.visibility = View.GONE
                binding.notFound.visibility = View.VISIBLE
            }
        })

        with(binding.rvBookmarkTvShows) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowsAdapter
        }
    }


    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tvShowEntity = tvShowsAdapter.getSwipedData(swipedPosition)
                tvShowEntity?.let { viewModel.setBookmark(it) }
                val snackbar =
                    Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { _ ->
                    tvShowEntity?.let { viewModel.setBookmark(it) }
                }
                snackbar.show()
            }
        }
    })

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFavoriteTvShowsBinding = null
    }
}
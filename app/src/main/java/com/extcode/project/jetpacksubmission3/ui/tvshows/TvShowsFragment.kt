package com.extcode.project.jetpacksubmission3.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.extcode.project.jetpacksubmission3.databinding.FragmentTvShowsBinding
import com.extcode.project.jetpacksubmission3.viewmodel.ViewModelFactory
import com.extcode.project.jetpacksubmission3.vo.Status

class TvShowsFragment : Fragment() {

    private var fragmentTvShowsBinding: FragmentTvShowsBinding? = null
    private val binding get() = fragmentTvShowsBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[TvShowsViewModel::class.java]

        val tvShowsAdapter = TvShowsAdapter()
        viewModel.getTvShows().observe(this, { tvShow ->
            if (tvShow != null) {
                when (tvShow.status) {
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.notFound.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.notFound.visibility = View.GONE
                        tvShowsAdapter.submitList(tvShow.data)
                        tvShowsAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        binding.notFound.visibility = View.VISIBLE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(binding.rvTvShows) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentTvShowsBinding = null
    }

}
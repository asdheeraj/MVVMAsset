package com.asset.mvvmasset.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.asset.mvvmasset.R
import com.asset.mvvmasset.data.util.Resource
import com.asset.mvvmasset.databinding.FragmentMoviesBinding
import com.asset.mvvmasset.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var moviesBinding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        return moviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribeObservers()
    }

    private fun initViews() {
        with(moviesBinding.rvMovies) {
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun subscribeObservers() {
        moviesViewModel.popularMovies.observe(viewLifecycleOwner, Observer { moviesResponse ->
            when (moviesResponse) {
                is Resource.Success -> {
                    hideProgressBar()
                    Log.d("Response: ", moviesResponse.data?.toString() ?: "")
                }
                is Resource.Loading -> {
                   showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(activity, moviesResponse.message ?: "", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun showProgressBar() {
        moviesBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        moviesBinding.progressBar.visibility = View.GONE
    }
}
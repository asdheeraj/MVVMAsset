package com.asset.mvvmasset.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.asset.mvvmasset.R
import com.asset.mvvmasset.databinding.FragmentMoviesBinding
import com.asset.mvvmasset.presentation.viewmodel.MoviesViewModel

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
        moviesViewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            
        })
    }
}
package com.adewijayanto.defilmsapp2.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adewijayanto.defilmsapp2.databinding.FragmentMovieBinding
import com.adewijayanto.defilmsapp2.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
    private lateinit var fragmentMoviesBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentMoviesBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val repository = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, repository)[MovieViewModel::class.java]
            val moviesAdapter = MovieAdapter()

            fragmentMoviesBinding.pbMovie.visibility = View.VISIBLE
            viewModel.getAllMovies().observe(viewLifecycleOwner, { movies ->
                fragmentMoviesBinding.pbMovie.visibility = View.GONE
                if (movies != null) {
                    moviesAdapter.setMovieList(movies)
                    moviesAdapter.notifyDataSetChanged()
                }
            })

            with(fragmentMoviesBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }
}
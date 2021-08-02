package com.adewijayanto.defilmsapp2.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adewijayanto.defilmsapp2.databinding.FragmentTvShowBinding
import com.adewijayanto.defilmsapp2.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val repository = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, repository)[TvShowViewModel::class.java]
            val tvShowAdapter = TvShowAdapter()

            fragmentTvShowBinding.pbTvShow.visibility = View.VISIBLE
            viewModel.getAllTvShow().observe(viewLifecycleOwner, { tvShow ->
                fragmentTvShowBinding.pbTvShow.visibility = View.GONE
                if (tvShow != null) {
                    tvShowAdapter.setTvShow(tvShow)
                    tvShowAdapter.notifyDataSetChanged()
                }
            })

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}
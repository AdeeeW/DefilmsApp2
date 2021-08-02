package com.adewijayanto.defilmsapp2.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adewijayanto.defilmsapp2.BuildConfig
import com.adewijayanto.defilmsapp2.R
import com.adewijayanto.defilmsapp2.data.source.localentity.MovieEntity
import com.adewijayanto.defilmsapp2.databinding.ListItemBinding
import com.adewijayanto.defilmsapp2.ui.detailmovie.DetailMovieActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

const val IMAGE_URL = BuildConfig.IMAGE_URL

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val listView = ArrayList<MovieEntity>()


    fun setMovieList(movies: List<MovieEntity>) {
        listView.clear()
        listView.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listView[position])
    }

    override fun getItemCount(): Int = listView.size

    inner class MovieViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            movie.apply {
                binding.apply {
                    val poster = StringBuilder("$IMAGE_URL${poster_path}").toString()
                    Glide.with(itemView)
                        .load(poster)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error)
                        )
                        .centerCrop()
                        .into(imgPoster)
                    tvTitle.text = movie.title
                    tvDeskripsi.text = movie.overview
                    tvDate.text = movie.release_date
                    barRating.rating = movie.vote_average / 2
                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                        intent.putExtra(DetailMovieActivity.EXTRA_ID, id)
                        itemView.context.startActivity(intent)
                    }
                }
            }
        }
    }
}
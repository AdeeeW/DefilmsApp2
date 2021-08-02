package com.adewijayanto.defilmsapp2.ui.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adewijayanto.defilmsapp2.data.source.localentity.GenreEntity
import com.adewijayanto.defilmsapp2.databinding.ListGenreBinding

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenresViewHolder>() {

    private var listGenres = ArrayList<GenreEntity>()

    fun setGenres(genre: List<GenreEntity>) {
        if (genre.isNullOrEmpty()) return
        this.listGenres.clear()
        this.listGenres.addAll(genre)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val itemsGenresBinding =
            ListGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenresViewHolder(itemsGenresBinding)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val movies = listGenres[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listGenres.size

    inner class GenresViewHolder(private val binding: ListGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: GenreEntity) {
            with(binding) {
                genre.name.also { rvDetailGenre.text = it }
            }
        }
    }


}
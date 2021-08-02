package com.adewijayanto.defilmsapp2.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adewijayanto.defilmsapp2.BuildConfig
import com.adewijayanto.defilmsapp2.R
import com.adewijayanto.defilmsapp2.data.source.localentity.TvShowEntity
import com.adewijayanto.defilmsapp2.databinding.ListItemBinding
import com.adewijayanto.defilmsapp2.ui.detailtvshow.DetailTvShowActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

const val IMAGE_URL = BuildConfig.IMAGE_URL

@Suppress("SENSELESS_COMPARISON")
class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private val listView = ArrayList<TvShowEntity>()

    fun setTvShow(tvShow: List<TvShowEntity>) {
        if (tvShow == null) return
        listView.clear()
        listView.addAll(tvShow)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvShowViewHolder, position: Int) {
        holder.bind(listView[position])
    }

    override fun getItemCount(): Int = listView.size

    inner class TvShowViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            tvShow.apply {
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
                    tvTitle.text = tvShow.name
                    tvDeskripsi.text = tvShow.overview
                    tvDate.text = tvShow.first_air_date
                    barRating.rating = tvShow.vote_average / 2
                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                        intent.putExtra(DetailTvShowActivity.EXTRA_ID, id)
                        itemView.context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(view)
    }

}
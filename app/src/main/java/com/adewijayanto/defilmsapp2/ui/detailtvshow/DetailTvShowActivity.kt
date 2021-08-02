package com.adewijayanto.defilmsapp2.ui.detailtvshow

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adewijayanto.defilmsapp2.BuildConfig
import com.adewijayanto.defilmsapp2.R
import com.adewijayanto.defilmsapp2.data.source.localentity.TvShowEntity
import com.adewijayanto.defilmsapp2.databinding.ActivityDetailTvShowBinding
import com.adewijayanto.defilmsapp2.databinding.ContentDetailTvShowBinding
import com.adewijayanto.defilmsapp2.ui.genre.GenreAdapter
import com.adewijayanto.defilmsapp2.ui.settings.SettingsActivity
import com.adewijayanto.defilmsapp2.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

const val IMAGE_URL = BuildConfig.IMAGE_URL

class DetailTvShowActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var contentDetailBinding: ContentDetailTvShowBinding
    private lateinit var activityDetailBinding: ActivityDetailTvShowBinding
    private lateinit var viewModelTvShow: DetailTvShowActivityViewModel
    private lateinit var genresAdapter: GenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        contentDetailBinding = activityDetailBinding.icContent
        setContentView(activityDetailBinding.root)

        supportActionBar?.title = "Detail Tv Show"

        val factory = ViewModelFactory.getInstance()
        viewModelTvShow =
            ViewModelProvider(this, factory)[DetailTvShowActivityViewModel::class.java]

        genresAdapter = GenreAdapter()

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_ID)

            viewModelTvShow.setSelectedTvShow(tvShowId)

            activityDetailBinding.progressBar.visibility = VISIBLE
            contentDetailBinding.svContent.visibility = GONE
            activityDetailBinding.fbShare.visibility = GONE


            viewModelTvShow.getTvShowsDetail()
                .observe(this@DetailTvShowActivity, { tvShow ->
                    activityDetailBinding.progressBar.visibility = GONE
                    contentDetailBinding.svContent.visibility = VISIBLE
                    activityDetailBinding.fbShare.visibility = VISIBLE

                    populateMoviesDetail(tvShow)
                })

        }

    }

    private fun shareApp(tvShowEntity: TvShowEntity) {
        val mimeType = "text/plain"
        tvShowEntity.apply {
            ShareCompat.IntentBuilder
                .from(this@DetailTvShowActivity)
                .setType(mimeType)
                .setChooserTitle("Bagikan Tv Show: \"${name}\", sekarang.")
                .setText("${name}\n${overview}\n${first_air_date}")
                .startChooser()
        }
    }

    private fun populateMoviesDetail(tvShowEntity: TvShowEntity) {
        tvShowEntity.apply {
            with(contentDetailBinding) {
                name.also { tvDetailJudul.text = it }
                overview.also { tvDetailDeskripsi.text = it }
                (vote_average / 2).also { barRating.rating = it }
                first_air_date.also { tvDetailDate.text = it }
                original_language.also { tvDetailBahasa.text = it }
                if (genres.isNotEmpty()) {
                    genresAdapter.setGenres(genres)
                    genresAdapter.notifyDataSetChanged()
                }

                with(contentDetailBinding.rvDetailGenre) {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = genresAdapter
                }

                val imagePath = StringBuilder("${IMAGE_URL}${poster_path}").toString()
                Glide.with(this@DetailTvShowActivity)
                    .load(imagePath)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivPoster)
                Glide.with(this@DetailTvShowActivity)
                    .load(imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .thumbnail()
                    .centerCrop()
                    .into(imgBg)

                activityDetailBinding.fbShare.setOnClickListener {
                    shareApp(tvShowEntity)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when (itemId) {
            R.id.settings -> {
                val mIntent = Intent(this, SettingsActivity::class.java)
                startActivity(mIntent)
            }
        }
    }
}
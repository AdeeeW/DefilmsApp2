package com.adewijayanto.defilmsapp2.ui.detailmovie

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
import com.adewijayanto.defilmsapp2.data.source.localentity.MovieEntity
import com.adewijayanto.defilmsapp2.databinding.ActivityDetailMovieBinding
import com.adewijayanto.defilmsapp2.databinding.ContentDetailMovieBinding
import com.adewijayanto.defilmsapp2.ui.genre.GenreAdapter
import com.adewijayanto.defilmsapp2.ui.settings.SettingsActivity
import com.adewijayanto.defilmsapp2.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

const val IMAGE_URL = BuildConfig.IMAGE_URL

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var contentDetailBinding: ContentDetailMovieBinding
    private lateinit var activityDetailBinding: ActivityDetailMovieBinding
    private lateinit var viewModelMovie: DetailMovieActivityViewModel
    private lateinit var genresAdapter: GenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailBinding = activityDetailBinding.icContent
        setContentView(activityDetailBinding.root)

        supportActionBar?.title = "Detail Movie"

        val factory = ViewModelFactory.getInstance()
        viewModelMovie = ViewModelProvider(this, factory)[DetailMovieActivityViewModel::class.java]

        genresAdapter = GenreAdapter()

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_ID)

            viewModelMovie.setSelectedMovie(movieId)

            activityDetailBinding.progressBar.visibility = VISIBLE
            contentDetailBinding.svContent.visibility = GONE
            activityDetailBinding.fbShare.visibility = GONE


            viewModelMovie.getMovieDetail()
                .observe(this@DetailMovieActivity, { movies ->
                    activityDetailBinding.progressBar.visibility = GONE
                    contentDetailBinding.svContent.visibility = VISIBLE
                    activityDetailBinding.fbShare.visibility = VISIBLE

                    populateMoviesDetail(movies)
                })

        }

    }

    private fun shareApp(moviesEntity: MovieEntity) {
        val mimeType = "text/plain"
        moviesEntity.apply {
            ShareCompat.IntentBuilder
                .from(this@DetailMovieActivity)
                .setType(mimeType)
                .setChooserTitle("Bagikan Film: \"${title}\", sekarang.")
                .setText("${title}\n${overview}\n${release_date}")
                .startChooser()
        }
    }

    private fun populateMoviesDetail(moviesEntity: MovieEntity) {
        moviesEntity.apply {
            with(contentDetailBinding) {
                title.also { tvDetailJudul.text = it }
                overview.also { tvDetailDeskripsi.text = it }
                (vote_average / 2).also { barRating.rating = it }
                release_date.also { tvDetailDate.text = it }
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
                Glide.with(this@DetailMovieActivity)
                    .load(imagePath)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivPoster)
                Glide.with(this@DetailMovieActivity)
                    .load(imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .thumbnail()
                    .centerCrop()
                    .into(imgBg)

                activityDetailBinding.fbShare.setOnClickListener {
                    shareApp(moviesEntity)
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
package com.dearwolves.samplerecords.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.dearwolves.core.interfaces.IMediaService
import com.dearwolves.core.interfaces.IStringService
import com.dearwolves.core.model.MediaResponse
import com.dearwolves.core.repository.LocalRepository
import com.dearwolves.samplerecords.R
import com.dearwolves.samplerecords.RecordApp
import com.dearwolves.samplerecords.databinding.ActivityMovieDetailsBinding
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    companion object {
        const val ITEM = "ITEM"
    }

    @Inject
    lateinit var mediaService: IMediaService

    @Inject
    lateinit var stringService: IStringService

    @Inject
    lateinit var localRepository: LocalRepository

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as RecordApp)
            .getRecordComponent()
            .inject(this@DetailActivity)

        val i = intent
        val mediaResponse:MediaResponse = i.getParcelableExtra(ITEM)

        viewModel = DetailViewModel(mediaResponse, localRepository)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = mediaResponse.trackName


        viewModel.isBookmarked.observe(this, Observer { t ->
            binding.bookmarkButton.setImageDrawable(
                ContextCompat.getDrawable(this@DetailActivity,
                    if(t) R.drawable.ic_star_black_24dp else R.drawable.ic_bookmark_black_24dp))
        })

        viewModel.init()

    }
}

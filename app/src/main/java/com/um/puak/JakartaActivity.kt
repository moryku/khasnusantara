package com.um.puak


import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Video.Thumbnails.VIDEO_ID
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import kotlinx.android.synthetic.main.activity_jakarta.*


class JakartaActivity: YouTubeBaseActivity(), YouTubeThumbnailView.OnInitializedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jakarta)

        videoLagu.initialize(getString(R.string.GOOGLE_API_KEY), this@JakartaActivity)
        videoLagu.setOnClickListener {
            startVideo("pgfsl_0NEo4")
        }

        videoTari.initialize(getString(R.string.GOOGLE_API_KEY), this@JakartaActivity)
        videoTari.setOnClickListener {
            startVideo("9oB6110x55w")
        }

        back.setOnClickListener {
            super.onBackPressed()
        }
    }

    fun startVideo(id: String) {
        startActivity(Intent(this@JakartaActivity, VideoYoutubeActivity::class.java).putExtra("data", id))

    }
    override fun onInitializationSuccess(thumnailView: YouTubeThumbnailView?, thumbnailLoader: YouTubeThumbnailLoader?) {
        var youTubeThumbnailLoader = thumbnailLoader
        if (thumnailView!!.equals(videoLagu)) {
            youTubeThumbnailLoader!!.setVideo("pgfsl_0NEo4")
        } else if (thumnailView!!.equals(videoTari)) {
            youTubeThumbnailLoader!!.setVideo("9oB6110x55w")
        }
    }

    override fun onInitializationFailure(
            youTubeThumbnailLoader: YouTubeThumbnailView?,
            thumbnailLoader: YouTubeInitializationResult?
    ) {

    }

}
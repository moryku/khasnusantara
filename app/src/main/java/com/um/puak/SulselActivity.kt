package com.um.puak


import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Video.Thumbnails.VIDEO_ID
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import kotlinx.android.synthetic.main.activity_sulsel.*


class SulselActivity: YouTubeBaseActivity(), YouTubeThumbnailView.OnInitializedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sulsel)

        videoLagu.initialize(getString(R.string.GOOGLE_API_KEY), this@SulselActivity)
        videoLagu.setOnClickListener {
            startVideo("Ia7ryH1YQCI")
        }

        videoTari.initialize(getString(R.string.GOOGLE_API_KEY), this@SulselActivity)
        videoTari.setOnClickListener {
            startVideo("TAS-4YNaVOA")
        }

        back.setOnClickListener {
            super.onBackPressed()
        }
    }

    fun startVideo(id: String) {
        startActivity(Intent(this@SulselActivity, VideoYoutubeActivity::class.java).putExtra("data", id))

    }

    override fun onInitializationSuccess(thumnailView: YouTubeThumbnailView?, thumbnailLoader: YouTubeThumbnailLoader?) {
        var youTubeThumbnailLoader = thumbnailLoader
        if (thumnailView!!.equals(videoLagu)) {
            youTubeThumbnailLoader!!.setVideo("Ia7ryH1YQCI")
        } else if (thumnailView!!.equals(videoTari)) {
            youTubeThumbnailLoader!!.setVideo("TAS-4YNaVOA")
        }
    }

    override fun onInitializationFailure(
            youTubeThumbnailLoader: YouTubeThumbnailView?,
            thumbnailLoader: YouTubeInitializationResult?
    ) {

    }
}


package com.um.puak

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Video.Thumbnails.VIDEO_ID
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import kotlinx.android.synthetic.main.activity_aceh.*


class AcehActivity: YouTubeBaseActivity(), YouTubeThumbnailView.OnInitializedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aceh)

        videoLagu.initialize(getString(R.string.GOOGLE_API_KEY), this@AcehActivity)
        videoLagu.setOnClickListener {
            startVideo("nXpCo7WXW7M")
        }

        videoTari.initialize(getString(R.string.GOOGLE_API_KEY), this@AcehActivity)
        videoTari.setOnClickListener {
            startVideo("nXpCo7WXW7M")
        }

        back.setOnClickListener {
            super.onBackPressed()
        }
    }

    fun startVideo(id: String) {
        startActivity(Intent(this@AcehActivity, VideoYoutubeActivity::class.java).putExtra("data", id))

    }
    override fun onInitializationSuccess(thumnailView: YouTubeThumbnailView?, thumbnailLoader: YouTubeThumbnailLoader?) {
        var youTubeThumbnailLoader = thumbnailLoader
        if (thumnailView!!.equals(videoLagu)) {
            youTubeThumbnailLoader!!.setVideo("nXpCo7WXW7M")
        } else if (thumnailView!!.equals(videoTari)) {
            youTubeThumbnailLoader!!.setVideo("nXpCo7WXW7M")
        }
    }

    override fun onInitializationFailure(
        youTubeThumbnailLoader: YouTubeThumbnailView?,
        thumbnailLoader: YouTubeInitializationResult?
    ) {

    }

}
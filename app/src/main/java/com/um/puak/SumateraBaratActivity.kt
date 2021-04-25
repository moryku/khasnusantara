package com.um.puak

import android.content.Intent
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import kotlinx.android.synthetic.main.activity_sumatera_barat.back
import kotlinx.android.synthetic.main.activity_sumatera_barat.videoLagu
import kotlinx.android.synthetic.main.activity_sumatera_barat.videoTari

class SumateraBaratActivity : YouTubeBaseActivity(), YouTubeThumbnailView.OnInitializedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sumatera_barat)

        videoLagu.initialize(getString(R.string.GOOGLE_API_KEY), this@SumateraBaratActivity)
        videoLagu.setOnClickListener {
            startVideo("9Hrs8VO7uIM")
        }

        videoTari.initialize(getString(R.string.GOOGLE_API_KEY), this@SumateraBaratActivity)
        videoTari.setOnClickListener {
            startVideo("1O_OYwnV6iw")
        }
        back.setOnClickListener {
            super.onBackPressed()
        }
    }

    fun startVideo(id: String) {
        startActivity(Intent(this@SumateraBaratActivity, VideoYoutubeActivity::class.java).putExtra("data", id))

    }
    override fun onInitializationSuccess(thumnailView: YouTubeThumbnailView?, thumbnailLoader: YouTubeThumbnailLoader?) {
        var youTubeThumbnailLoader = thumbnailLoader
        if (thumnailView!!.equals(videoLagu)) {
            youTubeThumbnailLoader!!.setVideo("9Hrs8VO7uIM")
        } else if (thumnailView!!.equals(videoTari)) {
            youTubeThumbnailLoader!!.setVideo("1O_OYwnV6iw")
        }
    }

    override fun onInitializationFailure(
            youTubeThumbnailLoader: YouTubeThumbnailView?,
            thumbnailLoader: YouTubeInitializationResult?
    ) {

    }

}
package com.um.puak

import android.content.Intent
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import kotlinx.android.synthetic.main.activity_jawa_barat.back
import kotlinx.android.synthetic.main.activity_jawa_barat.videoLagu
import kotlinx.android.synthetic.main.activity_jawa_barat.videoTari

class JawaBaratActivity : YouTubeBaseActivity(), YouTubeThumbnailView.OnInitializedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jawa_barat)

        videoLagu.initialize(getString(R.string.GOOGLE_API_KEY), this@JawaBaratActivity)
        videoLagu.setOnClickListener {
            startVideo("atFo0JEblzo")
        }

        videoTari.initialize(getString(R.string.GOOGLE_API_KEY), this@JawaBaratActivity)
        videoTari.setOnClickListener {
            startVideo("t37IEPccXRg")
        }
        back.setOnClickListener {
            super.onBackPressed()
        }
    }

    fun startVideo(id: String) {
        startActivity(Intent(this@JawaBaratActivity, VideoYoutubeActivity::class.java).putExtra("data", id))

    }
    override fun onInitializationSuccess(thumnailView: YouTubeThumbnailView?, thumbnailLoader: YouTubeThumbnailLoader?) {
        var youTubeThumbnailLoader = thumbnailLoader
        if (thumnailView!!.equals(videoLagu)) {
            youTubeThumbnailLoader!!.setVideo("atFo0JEblzo")
        } else if (thumnailView!!.equals(videoTari)) {
            youTubeThumbnailLoader!!.setVideo("t37IEPccXRg")
        }
    }

    override fun onInitializationFailure(
            youTubeThumbnailLoader: YouTubeThumbnailView?,
            thumbnailLoader: YouTubeInitializationResult?
    ) {

    }

}
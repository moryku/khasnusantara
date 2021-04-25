package com.um.puak

import android.content.Intent
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import kotlinx.android.synthetic.main.activity_aceh.*
import kotlinx.android.synthetic.main.activity_sumatera_selatan.*
import kotlinx.android.synthetic.main.activity_sumatera_selatan.back
import kotlinx.android.synthetic.main.activity_sumatera_selatan.videoLagu
import kotlinx.android.synthetic.main.activity_sumatera_selatan.videoTari

class SumateraSelatanActivity  : YouTubeBaseActivity(), YouTubeThumbnailView.OnInitializedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sumatera_selatan)

        videoLagu.initialize(getString(R.string.GOOGLE_API_KEY), this@SumateraSelatanActivity)
        videoLagu.setOnClickListener {
            startVideo("2G-qOBRrhqQ")
        }

        videoTari.initialize(getString(R.string.GOOGLE_API_KEY), this@SumateraSelatanActivity)
        videoTari.setOnClickListener {
            startVideo("1BQM836-e84")
        }
        back.setOnClickListener {
            super.onBackPressed()
        }
    }

    fun startVideo(id: String) {
        startActivity(Intent(this@SumateraSelatanActivity, VideoYoutubeActivity::class.java).putExtra("data", id))

    }
    override fun onInitializationSuccess(thumnailView: YouTubeThumbnailView?, thumbnailLoader: YouTubeThumbnailLoader?) {
        var youTubeThumbnailLoader = thumbnailLoader
        if (thumnailView!!.equals(videoLagu)) {
            youTubeThumbnailLoader!!.setVideo("2G-qOBRrhqQ")
        } else if (thumnailView!!.equals(videoTari)) {
            youTubeThumbnailLoader!!.setVideo("1BQM836-e84")
        }
    }

    override fun onInitializationFailure(
            youTubeThumbnailLoader: YouTubeThumbnailView?,
            thumbnailLoader: YouTubeInitializationResult?
    ) {

    }

}
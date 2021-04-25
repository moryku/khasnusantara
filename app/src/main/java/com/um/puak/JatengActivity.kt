package com.um.puak


import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Video.Thumbnails.VIDEO_ID
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import kotlinx.android.synthetic.main.activity_jawa_tengah.*


class JatengActivity: YouTubeBaseActivity(), YouTubeThumbnailView.OnInitializedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jawa_tengah)

        videoLagu.initialize(getString(R.string.GOOGLE_API_KEY), this@JatengActivity)
        videoLagu.setOnClickListener {
            startVideo("ZDGhJ4ToP2k")
        }

        videoTari.initialize(getString(R.string.GOOGLE_API_KEY), this@JatengActivity)
        videoTari.setOnClickListener {
            startVideo("aBD2aSde_RE")
        }

        back.setOnClickListener {
            super.onBackPressed()
        }
    }

    fun startVideo(id: String) {
        startActivity(Intent(this@JatengActivity, VideoYoutubeActivity::class.java).putExtra("data", id))

    }
    override fun onInitializationSuccess(thumnailView: YouTubeThumbnailView?, thumbnailLoader: YouTubeThumbnailLoader?) {
        var youTubeThumbnailLoader = thumbnailLoader
        if (thumnailView!!.equals(videoLagu)) {
            youTubeThumbnailLoader!!.setVideo("ZDGhJ4ToP2k")
        } else if (thumnailView!!.equals(videoTari)) {
            youTubeThumbnailLoader!!.setVideo("aBD2aSde_RE")
        }
    }

    override fun onInitializationFailure(
            youTubeThumbnailLoader: YouTubeThumbnailView?,
            thumbnailLoader: YouTubeInitializationResult?
    ) {

    }

}
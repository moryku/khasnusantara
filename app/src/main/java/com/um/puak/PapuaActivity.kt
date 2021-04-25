package com.um.puak


import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Video.Thumbnails.VIDEO_ID
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import kotlinx.android.synthetic.main.activity_papua.*


class PapuaActivity: YouTubeBaseActivity(), YouTubeThumbnailView.OnInitializedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_papua)

        videoLagu.initialize(getString(R.string.GOOGLE_API_KEY), this@PapuaActivity)
        videoLagu.setOnClickListener {
            startVideo("HY_gTFciG70")
        }

        videoTari.initialize(getString(R.string.GOOGLE_API_KEY), this@PapuaActivity)
        videoTari.setOnClickListener {
            startVideo("fKLneHq_CXc")
        }

        back.setOnClickListener {
            super.onBackPressed()
        }
    }

    fun startVideo(id: String) {
        startActivity(Intent(this@PapuaActivity, VideoYoutubeActivity::class.java).putExtra("data", id))

    }
    override fun onInitializationSuccess(thumnailView: YouTubeThumbnailView?, thumbnailLoader: YouTubeThumbnailLoader?) {
        var youTubeThumbnailLoader = thumbnailLoader
        if (thumnailView!!.equals(videoLagu)) {
            youTubeThumbnailLoader!!.setVideo("HY_gTFciG70")
        } else if (thumnailView!!.equals(videoTari)) {
            youTubeThumbnailLoader!!.setVideo("fKLneHq_CXc")
        }
    }

    override fun onInitializationFailure(
            youTubeThumbnailLoader: YouTubeThumbnailView?,
            thumbnailLoader: YouTubeInitializationResult?
    ) {

    }

}
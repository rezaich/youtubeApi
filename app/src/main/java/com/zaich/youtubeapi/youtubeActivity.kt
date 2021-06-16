package com.zaich.youtubeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "liJVSwOiiwg"

class youtubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val TAG = "youtubeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = layoutInflater.inflate(
            R.layout.activity_youtube,null
        ) as ConstraintLayout
        setContentView(layout)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(playerView)

        playerView.initialize("AIzaSyA9Rr87Qxcoa5SQ3VIIguVIx-I_ZZoHcMY",this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youtubePlayer: YouTubePlayer?,
        wasRestored : Boolean
    ) {
        Log.d(
            TAG,"onInitializationSuccess: Provider is ${provider?.javaClass}"
        )
        Log.d(
            TAG,"onInitializationSuccess: youtubePlayer is ${youtubePlayer?.javaClass}"
        )
        Toast.makeText(this, "onInitialized Youtube player successfully", Toast.LENGTH_SHORT).show()

        youtubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
        youtubePlayer?.setPlaybackEventListener(playbackEventListener)

        if (!wasRestored){
            youtubePlayer?.cueVideo(YOUTUBE_VIDEO_ID)
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youtbeInitializationResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0

        if (youtbeInitializationResult?.isUserRecoverableError == true){
            youtbeInitializationResult.getErrorDialog(this,REQUEST_CODE).show()
        }else{
            val errorMessage = "There was an error initializing the youtubePlayer" +
            "($youtbeInitializationResult)"
            Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener{
        override fun onPlaying() {
            Toast.makeText(this@youtubeActivity, "GOOD , VIDEO PLAYING OK", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@youtubeActivity, "Video has Paused", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
            Toast.makeText(this@youtubeActivity, "VIDEO HAS STOPPED", Toast.LENGTH_SHORT).show()
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onSeekTo(p0: Int) {
        }
    }

    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener{
        override fun onLoading() {
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onAdStarted() {
            Toast.makeText(this@youtubeActivity, "CLICK AD NOW , MAKE THE VIDEO CREATOR BEEN RICH", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoStarted() {
            Toast.makeText(this@youtubeActivity, "video has started", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(this@youtubeActivity, "END", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }

    }
}
package com.example.searching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : YouTubeBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        var selectedVideoID = intent.getStringExtra("VIDEO_ID")
        var selectedVideoTitle =
            intent.getStringExtra("VIDEO_TITLE")

        Toast.makeText(applicationContext,""+ selectedVideoTitle,
            Toast.LENGTH_SHORT).show()
        var KEY= "AIzaSyBB9bmQXE8yli4Or9GPoOeEloprQlpa3GA"
        var playerView = findViewById<YouTubePlayerView>(R.id.youtube_playerview)
        playerView.initialize(KEY, object : YouTubePlayer.OnInitializedListener {

            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, restored: Boolean) {
                if (!restored) {
                    youTubePlayer.cueVideo(selectedVideoID)
                }
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {
                Toast.makeText(applicationContext, "Something went wrong..!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
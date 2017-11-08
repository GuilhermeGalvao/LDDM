package com.example.tp2;


import android.content.Intent;
import android.os.Bundle;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    private static final String API_KEY = "  AIzaSyBfQ7u4FqPvqpNTsTo25I62_6kDxOxFhJs ";
    private String ID_VIDEO = "";
    private YouTubePlayerView youtube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        Intent intent = getIntent();
        //ID_VIDEO = intent.getStringExtra(EXTRA_VIDEOSLINK);

        youtube = (YouTubePlayerView) findViewById(R.id.youtube);
        youtube.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean loadAgain) {
        String entrada[];
        entrada = ID_VIDEO.split("be/");
        ID_VIDEO = entrada[1];
        if(!loadAgain){
            youTubePlayer.cueVideo(ID_VIDEO);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this,"onInitializationFailure()",Toast.LENGTH_SHORT).show();
    }
}

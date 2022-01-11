package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioBookActivity extends AppCompatActivity {
    MediaPlayer player;
    static int localPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_book);
        Intent myIntent=getIntent();
        localPosition=myIntent.getIntExtra("index",0);


    }


    public void play(View v){
        String audioUrl=null;
        if(localPosition==0){
            audioUrl = "https://firebasestorage.googleapis.com/v0/b/ebookprototype.appspot.com/o/tarkan-dudu.mp3?alt=media&token=ecd7c473-d98c-42f1-a26e-b617300e0be3";
        }
        else if(localPosition==1){
            audioUrl = "https://firebasestorage.googleapis.com/v0/b/ebookprototype.appspot.com/o/mustafa-sandal-aya-benzer.mp3?alt=media&token=078adb95-4141-4f90-a683-d43efa99bbeb";
        }
        if(player == null){
            //player = MediaPlayer.create(this,R.raw.tarkan-dudu);
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                player.setDataSource(audioUrl);
                player.prepare();
                player.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
            // below line is use to display a toast message.
            Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show();

        }

        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopPlayer();
            }
        });
    }
    public void pause(View v){
        if(player!=null) player.pause();

    }
    public void stop(View v){
        stopPlayer();
    }

    public void stopPlayer (){
        if(player!=null) {
            player.release();
            player=null;
            Toast.makeText(this, "Media Player released",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}
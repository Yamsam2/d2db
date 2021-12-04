package com.example.d2db;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;


import androidx.appcompat.app.AppCompatActivity;

public class MainPage_Act extends AppCompatActivity {
    private VideoView videov;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mainpage);

            final MediaPlayer mPlayer;

            EditText searchbar = (EditText) findViewById(R.id.searchbar);
            Button btnSearch = (Button) findViewById(R.id.btnSearch);
            Button btnHome = (Button) findViewById(R.id.btnSearchHome);
            Button btnbookmark_list = (Button) findViewById(R.id.btn_bookmark_list);

            mPlayer = MediaPlayer.create(this,R.raw.dia2);
            Switch bgm = (Switch) findViewById(R.id.bgm);


            //백그라운드 비디오
            videov = (VideoView) findViewById(R.id.video_view);
            Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.backvideoss);
            videov.setVideoURI(uri);
            videov.start();
            //무한루프
            videov.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });



            //백그라운드 음악재생
            bgm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPlayer.setVolume(45,45);
                    mPlayer.setLooping(true);
                    if (bgm.isChecked()==true){
                        mPlayer.start();
                        Toast.makeText(getApplicationContext(),"BGM 작동",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        mPlayer.pause();
                        Toast.makeText(getApplicationContext(),"BGM 정지",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //카테고리보기 눌렀을때
            btnHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), com.example.d2db.MainActivity.class);
                    startActivity(intent);
                }
            });
            //카테고리보기 눌렀을때
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), com.example.d2db.ItemPage.class);
                    startActivity(intent);
                }
            });
            //즐겨찾기 눌렀을때
            btnbookmark_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), com.example.d2db.BookmarkPage.class);
                    startActivity(intent);
                }
            });



        }

//배경 비디오(videov) 나가거나 다른창에서 돌아왔을 때 다시시작
    @Override
    protected void onPostResume() {
        videov.resume();
        super.onPostResume();
    }

    @Override
    protected void onRestart() {
        videov.start();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        videov.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        videov.stopPlayback();
        super.onDestroy();
    }

}
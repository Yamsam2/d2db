package com.example.d2db;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class MainPage_Act extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mainpage);

            final MediaPlayer mPlayer;

            EditText searchbar = (EditText) findViewById(R.id.searchbar);
            Button btnSearch = (Button) findViewById(R.id.btnSearch);
            Button btnHome = (Button) findViewById(R.id.btnSearchHome);

            mPlayer = MediaPlayer.create(this,R.raw.dia2);
            Switch bgm = (Switch) findViewById(R.id.bgm);



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



        }
    }
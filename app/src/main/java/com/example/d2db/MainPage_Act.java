package com.example.d2db;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;


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
                    if (bgm.isChecked()==true)
                        mPlayer.start();
                    else
                        mPlayer.stop();
                }
            });

            //검색버튼 눌렀을때
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                }
            });


            //카테고리보기 눌렀을때
            btnHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ItemPage.class);
                    startActivity(intent);
                }
            });


        }
    }
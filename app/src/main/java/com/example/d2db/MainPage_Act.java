package com.example.d2db;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainPage_Act extends AppCompatActivity {
    private VideoView videov;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;

    private ImageButton btn_drawer;
    private DrawerLayout drawer;
    private NavigationView NavView; //네비바

    //뒤로가기 버튼눌렀을때 네비바 열려있으면 네비바 닫기
    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(Gravity.END)){
            drawer.closeDrawer(Gravity.END);
        }else{
            super.onBackPressed();
        }
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mainpage);

            //네비게이션 드로어(툴바)
            //네비바 옵션
            androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowCustomEnabled(true);//커스텀사용
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);//타이틀 표시
//            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

            //드로어 클릭
//            btn_drawer.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });
            drawer = findViewById(R.id.drawer_layout);
            btn_drawer = (ImageButton) findViewById(R.id.btn_drawer);

            //메뉴 드로어 버튼 누르면 오른쪽에서 나오게
            btn_drawer.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("WrongConstant")
                @Override
                public void onClick(View v) {
                    if (drawer.isDrawerOpen(Gravity.END)) {
                        drawer.closeDrawer(Gravity.END);
                    } else {
                        drawer.openDrawer(Gravity.END);
                    }

                }
            });


            //네비 메뉴 온클릭 이벤트처리
            NavView = (NavigationView) findViewById(R.id.nav_view);
            NavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @SuppressLint("WrongConstant")
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.menu_item1:{
                            Intent intent = new Intent(getApplicationContext(), com.example.d2db.MainActivity.class);
                            startActivity(intent);
                            drawer.closeDrawer(Gravity.END);
                            break;
                        }
                        case R.id.menu_item2:{
                            Intent intent = new Intent(getApplicationContext(), com.example.d2db.BookmarkPage.class);
                            startActivity(intent);
                            drawer.closeDrawer(Gravity.END);
                            break;
                        }
                        case R.id.btn_blizzard:{
                            Intent locations = new Intent(Intent.ACTION_VIEW);
                            locations.setData(Uri.parse("https://diablo2.blizzard.com/ko-kr/"));
                            startActivity(locations);
                            drawer.closeDrawer(Gravity.END);
                            break;
                        }
                        case R.id.btn_inven:{
                            Intent locations = new Intent(Intent.ACTION_VIEW);
                            locations.setData(Uri.parse("https://diablo2.inven.co.kr/"));
                            startActivity(locations);
                            drawer.closeDrawer(Gravity.END);
                            break;
                        }

                    }
                    return false;
                }
            });






            final MediaPlayer mPlayer;

//            EditText searchbar = (EditText) findViewById(R.id.searchbar);
            SearchView searchbar = (SearchView) findViewById(R.id.searchbar);
//            Button btnmenu = (Button) findViewById(R.id.btnmenu);

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

            //검색창




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

            //검색 눌렀을때
//            btnmenu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getApplicationContext(), com.example.d2db.ItemPage.class);
//                    startActivity(intent);
//                }
//            });



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
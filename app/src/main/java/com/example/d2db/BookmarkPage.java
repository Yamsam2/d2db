package com.example.d2db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

public class BookmarkPage extends AppCompatActivity {
    ListView listView;
    ItemDTO_Adapter adapter;

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
        setContentView(R.layout.bookmark_page);

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


    }
}
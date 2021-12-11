package com.example.d2db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ItemDTO_Adapter itemdtoAdapter;
    Item_Service item_service;
    LinearLayoutManager manager;
    public static Context context_main;

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


    ArrayAdapter<String> adapter_items;
    Spinner spn_category;
    Spinner spn_items;
    SearchView search;
    String item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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





        context_main = MainActivity.this;

        recyclerView = findViewById(R.id.item_recyclerView);
        search = findViewById(R.id.list_SearchView);

        //SearchView의 검색 이벤트
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //검색버튼을 눌렀을 경우
            @Override
            public boolean onQueryTextSubmit(String query) {
                item= query;
                Search();
                return true;
            }

            //텍스트가 바뀔때마다 호출
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });



        //레이아웃 설정
        manager= new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);


        final String[] category = {
                "무기", "방어구", "기타", "룬워드"
        };

        final String[] weapon = {
                "대거","한손검","양손검","클럽","메이스","해머","한손도끼","양손도끼","스태프",
                "원드","셉터","스피어","폴암","재벌린","보우","크로스보우","스로잉","클러","오브"
        };

        final String[] armour = {
                "헬름","아머","쉴드","글러브","부츠","벨트","서클릿"
        };

        final String[] etc = {
                "반지","목걸이","주얼","부적"
        };

        final String[] rune = {
                "무기","방어구","룬정보"
        };


        spn_category = (Spinner) findViewById(R.id.spn_category);
        spn_items = (Spinner) findViewById(R.id.spn_items);

        ArrayAdapter<String> adapter_category;


        adapter_category = new ArrayAdapter<String>(this, R.layout.spinner_style,category);
        spn_category.setAdapter(adapter_category);


        //카테고리 분류
        // 앞쪽 스피너를 눌렀을 때 뒤쪽 스피너의 항목이 바뀜
        spn_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spn_category.getSelectedItem().equals("무기")){
                    adapter_items = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_style,weapon);
                    spn_items.setAdapter(adapter_items);
                }
                else if (spn_category.getSelectedItem().equals("방어구")){
                    adapter_items = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_style,armour);
                    spn_items.setAdapter(adapter_items);
                }else if (spn_category.getSelectedItem().equals("기타")){
                    adapter_items = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_style,etc);
                    spn_items.setAdapter(adapter_items);
                }
                else if (spn_category.getSelectedItem().equals("룬워드")){
                    adapter_items = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_style,rune);
                    spn_items.setAdapter(adapter_items);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //스피너에서 선택된 아이템에 대한 리스트를 보여준다
        spn_items.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*상세보기 */
                //db 서버와 연결하고 전체보기 메소드를 불러온다
                item_service= Retrofit2_Client.getInstance().getItemService();
                Call<List<ItemDTO>> call1= item_service.find_Category(spn_items.getSelectedItem().toString());
                call1.enqueue(new Callback<List<ItemDTO>>() {
                    @Override
                    public void onResponse(Call<List<ItemDTO>> call, Response<List<ItemDTO>> response) {
                        Log.d("data", "응답 받은데이터: "+response.body());
                        List<ItemDTO> item= response.body();
                        itemdtoAdapter= new ItemDTO_Adapter(item);
                        recyclerView.setAdapter(itemdtoAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<ItemDTO>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void Search(){
        item_service= Retrofit2_Client.getInstance().getItemService();
        Call<List<ItemDTO>> call1= item_service.search(item);
        call1.enqueue(new Callback<List<ItemDTO>>() {
            @Override
            public void onResponse(Call<List<ItemDTO>> call, Response<List<ItemDTO>> response) {
                Log.d("data", "응답 받은데이터: "+response.body());
                List<ItemDTO> item= response.body();
                itemdtoAdapter= new ItemDTO_Adapter(item);
                recyclerView.setAdapter(itemdtoAdapter);
            }

            @Override
            public void onFailure(Call<List<ItemDTO>> call, Throwable t) {

            }
        });
    }

}
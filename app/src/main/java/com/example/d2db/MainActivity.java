package com.example.d2db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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


    ArrayAdapter<String> adapter_items;
    Spinner spn_category;
    Spinner spn_items;
    SearchView search;
    String item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
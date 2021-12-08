package com.example.d2db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private RecyclerView recyclerView;
    private ItemDTO_Adapter itemdtoAdapter;
    LinearLayoutManager manager;
    ArrayAdapter<String> adapter_items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



        Spinner spn_category = (Spinner) findViewById(R.id.spn_category);
        Spinner spn_items = (Spinner) findViewById(R.id.spn_items);

        ArrayAdapter<String> adapter_category;


        adapter_category = new ArrayAdapter<String>(this, R.layout.spinner_style,category);
        spn_category.setAdapter(adapter_category);


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

        Item_Service item_service = Retrofit2_Client.getInstance().getItemService();
        Call<List<ItemDTO>> call = item_service.findAll();

        call.enqueue(new Callback<List<ItemDTO>>() {
            @Override
            public void onResponse(Call<List<ItemDTO>> call, Response<List<ItemDTO>> response) {
                List<ItemDTO> itemList = response.body();
                recyclerView = findViewById(R.id.item_recyclerView);
                manager
                        = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);

                recyclerView.setLayoutManager(manager);

                itemdtoAdapter = new ItemDTO_Adapter(itemList);
                recyclerView.setAdapter(itemdtoAdapter);
                Log.d("data","onResponse: 응답 받은 데이터 :"+itemList);

            }

            @Override
            public void onFailure(Call<List<ItemDTO>> call, Throwable t) {
                Log.d("fail","onResponse: 데이터 불러오기 실패");

            }
        });









    }
}
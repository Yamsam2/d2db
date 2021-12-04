package com.example.d2db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ListItemAdapter adapter;


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



        //카테고리 검색 리스트뷰
        listView = findViewById(R.id.item_listview_category);
        adapter = new ListItemAdapter();
        adapter.addItem(new ListItem
                (ContextCompat.getDrawable(this, R.drawable.dager1),
                        "디글러 더크\n"+"(The Diggler Dirk)",
                        "한손 피해: 4 - 13\n" + "요구 레벨: 11\n" + "필요 민첩: 25\n" + "내구도: 20\n" + "기본 무기 속도: [0]",
                        "피해 50% 증가\n" + "대상의 방어력 무시\n" + "공격 속도 +30%\n" + "냉기 저항 +25%\n" + "화염 저항 +25%\n" + "민첩 +10"));
        adapter.addItem(new ListItem
                (ContextCompat.getDrawable(this, R.drawable.dager2),
                        "속임수 대거\n" + "(Gull Dagger)",
                        "한손 피해: 2 - 19\n" + "요구 레벨: 4\n" + "내구도: 16\n" + "기본 무기 속도: [-20]",
                        "피해 1 - 15 추가\n" + "마법 아이템 발견 확률 100% 증가\n" + "마나 -5"));
        listView.setAdapter(adapter);







    }
}
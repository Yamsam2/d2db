package com.example.d2db;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ItemPage extends AppCompatActivity {
    ListView listView;
    ListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_page);


        //상세보기 아이탬 가져오기
        listView = findViewById(R.id.item_listview_detail);
        adapter = new ListItemAdapter();
        adapter.addItem(new ListItem
                (ContextCompat.getDrawable(this, R.drawable.dager1), "겁내 쌘 대거","음","두방"));
        listView.setAdapter(adapter);

    }


}

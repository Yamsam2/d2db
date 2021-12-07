package com.example.d2db;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemPage extends AppCompatActivity {
    ListView listView;
    ItemDTO_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_page);

        Button btnSearchHome = (Button) findViewById(R.id.btnSearchHome);
        Button btn_bookmark_list = (Button) findViewById(R.id.btn_bookmark_list);

        //카테고리버튼
        btnSearchHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        //즐겨찾기버튼
        btn_bookmark_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BookmarkPage.class);
                startActivity(intent);
            }
        });


    }


}

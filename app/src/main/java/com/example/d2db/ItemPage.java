package com.example.d2db;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class ItemPage extends AppCompatActivity {
    ListView listView;
    ItemDTO_Adapter adapter;
    private int likecount = 0;
    private int unlikecount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_page);

        Button btnSearchHome = (Button) findViewById(R.id.btnSearchHome);
        Button btn_bookmark_list = (Button) findViewById(R.id.btn_bookmark_list);
        ImageButton btnLike = (ImageButton) findViewById(R.id.btnLike);
        ImageButton btnUnlike = (ImageButton) findViewById(R.id.btnUnlike);
        TextView cntLike = (TextView) findViewById(R.id.cntLike);
        TextView cntUnlike = (TextView) findViewById(R.id.cntUnlike);


        //좋아요버튼 이벤트
        btnLike.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                likecount++;
                cntLike.setText(likecount+"");
            }
        });

        //싫어요버튼 이벤트
        btnUnlike.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                unlikecount++;
                cntUnlike.setText("-"+unlikecount+"");
            }
        });

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

        //상세보기 아이템 가져오기
//        listView = findViewById(R.id.item_listview_detail);
//        adapter = new ListItemAdapter();
//        adapter.addItem(new ListItem
//                (ContextCompat.getDrawable(this, R.drawable.dager1), "겁내 쌘 대거","음","두방"));
//        listView.setAdapter(adapter);

    }


}

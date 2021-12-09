package com.example.d2db;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.List;

import retrofit2.Call;

public class ItemPage extends AppCompatActivity {
    ListView listView;
    ItemDTO_Adapter adapter;
    private int likeCount = 0;
    private int unlikeCount = 0;
    private String likeAction = "";
    private String unlikeAction = "";
    TextView cntLike;
    TextView cntUnlike;
    RadioButton rdolike;
    RadioButton rdoUnlike;
    Item_Service item_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_page);

        Button btnSearchHome = (Button) findViewById(R.id.btnSearchHome);
        Button btn_bookmark_list = (Button) findViewById(R.id.btn_bookmark_list);
        rdolike = (RadioButton) findViewById(R.id.rdoLike);
        rdoUnlike = (RadioButton) findViewById(R.id.rdoHate);
        RadioGroup checkItem = (RadioGroup) findViewById(R.id.checkItem);
        cntLike = (TextView) findViewById(R.id.cntLike);
        cntUnlike = (TextView) findViewById(R.id.cntUnlike);


//        item_service= Retrofit2_Client.getInstance().getItemService();
//        Call<List<ItemDTO>> call1= item_service.findAll()

        Intent in_intent= getIntent();
        //final int position=  in_intent.getIntExtra("position",0);
        final String name=  in_intent.getStringExtra("name");
        TextView itemname = findViewById(R.id.detail_item_name);
        itemname.setText(name);



        //좋아요 버튼
        rdolike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(likeAction == "" && unlikeAction == ""){
                    likeCount += 1;
                    likeAction = "liked";
                    cntLike.setText(String.valueOf(likeCount));
                    cntUnlike.setText(String.valueOf(unlikeCount));
                    Toast.makeText(getApplicationContext(), "좋아요", Toast.LENGTH_SHORT).show();

                }else if(likeAction == "" && unlikeAction == "unliked"){
                    likeCount += 1;
                    unlikeCount -= 1;
                    likeAction = "liked";
                    unlikeAction = "";
                    cntLike.setText(String.valueOf(likeCount));
                    cntUnlike.setText(String.valueOf(unlikeCount));
                    Toast.makeText(getApplicationContext(), "좋아요", Toast.LENGTH_SHORT).show();

                }else if (likeAction == "liked" && unlikeAction == ""){
                    likeCount -= 1;
                    likeAction = "";
                    cntLike.setText(String.valueOf(likeCount));
                    cntUnlike.setText(String.valueOf(unlikeCount));
                    checkItem.clearCheck();

                }



            }
        });

        //싫어요 버튼
        rdoUnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(unlikeAction == "" && likeAction ==""){
                    unlikeCount += 1;
                    unlikeAction = "unliked";
                    cntLike.setText(String.valueOf(likeCount));
                    cntUnlike.setText(String.valueOf(unlikeCount));
                    Toast.makeText(getApplicationContext(), "싫어요", Toast.LENGTH_SHORT).show();

                }else if(unlikeAction == "" && likeAction == "liked"){
                    unlikeCount += 1;
                    likeCount -= 1;
                    unlikeAction = "unliked";
                    likeAction = "";
                    cntLike.setText(String.valueOf(likeCount));
                    cntUnlike.setText(String.valueOf(unlikeCount));
                    Toast.makeText(getApplicationContext(), "싫어요", Toast.LENGTH_SHORT).show();

                }else if(unlikeAction == "unliked" && likeAction == ""){
                    unlikeCount -= 1;
                    unlikeAction = "";
                    cntLike.setText(String.valueOf(likeCount));
                    cntUnlike.setText(String.valueOf(unlikeCount));
                    checkItem.clearCheck();

                }






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

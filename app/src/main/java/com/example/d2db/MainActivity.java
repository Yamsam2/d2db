package com.example.d2db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        EditText searchbar = (EditText) findViewById(R.id.searchbar);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        Button btnHome = (Button) findViewById(R.id.btnSearchHome);

        //카테고리보기 눌렀을때
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });





    }
}
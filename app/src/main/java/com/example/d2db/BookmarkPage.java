package com.example.d2db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ListView;

public class BookmarkPage extends AppCompatActivity {
    ListView listView;
    ListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark_page);

        //listview 참조
        listView = findViewById(R.id.item_listview);
        adapter = new ListItemAdapter();

        adapter.addItem(new ListItem
                (ContextCompat.getDrawable(this, R.drawable.dager1), "겁내 쌘 대거","그냥쌔","한방"));
        adapter.addItem(new ListItem
                (ContextCompat.getDrawable(this, R.drawable.dager1), "겁내 쌘 대거","그냥쌔","한방"));
        adapter.addItem(new ListItem
                (ContextCompat.getDrawable(this, R.drawable.dager1), "겁내 쌘 대거","그냥쌔","한방"));
        listView.setAdapter(adapter);
    }
}
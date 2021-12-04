package com.example.d2db;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class ListItem {
    private Drawable item_img;
    private String text_item_name;
    private String item_stat;
    private String item_option;
    private Button btn_bookmark;


    //getter
    public Drawable getItem_img() {
        return item_img;
    }

    public String getText_item_name() {
        return text_item_name;
    }

    public String getItem_stat() {
        return item_stat;
    }

    public String getItem_option() {
        return item_option;
    }

//    public Button getBtn_bookmark() {
//        return btn_bookmark;
//    }


    //setter
    public void setItem_img(Drawable item_img) {
        this.item_img = item_img;
    }

    public void setText_item_name(String text_item_name) {
        this.text_item_name = text_item_name;
    }

    public void setItem_stat(String item_stat) {
        this.item_stat = item_stat;
    }

    public void setItem_option(String item_option) {
        this.item_option = item_option;
    }

//    public void setBtn_bookmark(Button btn_bookmark) {
//        this.btn_bookmark = btn_bookmark;
//    }

    ListItem(Drawable item_img, String text_item_name, String item_stat, String item_option){
        this.item_img = item_img;
        this.text_item_name = text_item_name;
        this.item_stat = item_stat;
        this.item_option = item_option;
//        this.btn_bookmark = btn_bookmark;
    }

}



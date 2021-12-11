package com.example.d2db;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.sql.Blob;

public class ItemDTO {
    private Long num;
    private String names;
    private String option1;
    private String option2;
    private String recommends;
    private String categorys;
    private Long likes;
    private Long dislikes;
    public ItemDTO(){

    }

    public ItemDTO(Long num, String names, String option1, String option2, String recommends, String categorys, Long likes, Long dislikes) {
        this.num = num;
        this.names = names;
        this.option1 = option1;
        this.option2 = option2;
        this.recommends = recommends;
        this.categorys = categorys;
        this.likes = likes;
        this.dislikes = dislikes;


    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getRecommends() {
        return recommends;
    }

    public void setRecommends(String recommends) {
        this.recommends = recommends;
    }

    public String getCategorys() {
        return categorys;
    }

    public void setCategorys(String categorys) {
        this.categorys = categorys;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDislikes() {
        return dislikes;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }
}

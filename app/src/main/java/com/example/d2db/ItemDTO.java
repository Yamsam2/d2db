package com.example.d2db;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.sql.Blob;

public class ItemDTO {
    private Long num;
    private String names;
    private String options;
    private String recommends;
    private String categorys;
    private Long likes;
    private Long dislikes;
    private Blob images;

    public ItemDTO(Long num, String names, String options, String recommends, String categorys, Long likes, Long dislikes, Blob images) {
        this.num = num;
        this.names = names;
        this.options = options;
        this.recommends = recommends;
        this.categorys = categorys;
        this.likes = likes;
        this.dislikes = dislikes;
        this.images = images;
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
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

    public Blob getImages() {
        return images;
    }

    public void setImages(Blob images) {
        this.images = images;
    }
}

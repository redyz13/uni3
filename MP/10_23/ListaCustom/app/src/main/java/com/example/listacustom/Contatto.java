package com.example.listacustom;

import android.graphics.drawable.Drawable;

public class Contatto {
    private String name, tel;
    private Drawable picture;

    public Contatto(String name, String tel, Drawable picture) {
        this.name = name;
        this.tel = tel;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public Drawable getPicture() {
        return picture;
    }
}

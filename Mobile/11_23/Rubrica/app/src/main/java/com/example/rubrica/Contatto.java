package com.example.rubrica;

import android.graphics.drawable.Drawable;

public class Contatto {
    private final String name;
    private final String phone;
    private final Drawable picture;

    public Contatto(String name, String phone, Drawable picture) {
        this.name = name;
        this.phone = phone;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Drawable getPicture() {
        return picture;
    }
}
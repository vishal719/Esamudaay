package com.example.esamudaay.models;

import android.widget.ImageView;

import androidx.cardview.widget.CardView;

public class CategoriesModel {
    String name;
    ImageView image;
    CardView card;

    public CategoriesModel(String name, ImageView image, CardView card) {
        this.name = name;
        this.image = image;
        this.card = card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public CardView getCard() {
        return card;
    }

    public void setCard(CardView card) {
        this.card = card;
    }
}


package com.example.esamudaay.models;

import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class SortModel {
    TextView text;
    MaterialCardView card;

    public SortModel(TextView text, MaterialCardView card) {
        this.text = text;
        this.card = card;
    }

    public TextView getText() {
        return text;
    }

    public void setText(TextView text) {
        this.text = text;
    }

    public MaterialCardView getCard() {
        return card;
    }

    public void setCard(MaterialCardView card) {
        this.card = card;
    }
}

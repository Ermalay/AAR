package com.example.libaar;


import io.realm.RealmObject;

public class IncomeText extends RealmObject {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }
}

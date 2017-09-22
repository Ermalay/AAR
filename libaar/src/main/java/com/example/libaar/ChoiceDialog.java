package com.example.libaar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmResults;

public class ChoiceDialog extends DialogFragment {

    SharedPreferences sharedPreferences;

    private Realm realm;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Выбор вида e-mail");
        builder.setMessage("Выберите - показывать e-mail до знака @ или показывать полностью");
        builder.setIcon(R.drawable.ok24);
        builder.setNegativeButton(R.string.not_show_at, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // сохраним в SharedPreferences наше булево-значение
                sharedPreferences = getContext().getSharedPreferences("my_settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("is_at", false);
                editor.apply();
                editor.commit();

                // отдаём во MyFlexbox текст, но в преференсах уже сидит нужное нам булево
                // MyFlexbox там у себя покажет это булево и спросит у GravatarChip как ему показать e-mail
                MyFlexbox myFlexbox = new MyFlexbox(getActivity(), getTextFromRealm());
                getActivity().setContentView(myFlexbox);

            }
        });
        builder.setPositiveButton(R.string.show_at, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // сохраним в SharedPreferences наше булево-значение
                sharedPreferences = getContext().getSharedPreferences("my_settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("is_at", true);
                editor.apply();
                editor.commit();

                // отдаём во MyFlexbox текст, но в преференсах уже сидит нужное нам булево
                // MyFlexbox там у себя покажет это булево и спросит у GravatarChip как ему показать e-mail
                MyFlexbox myFlexbox = new MyFlexbox(getActivity(), getTextFromRealm());
                getActivity().setContentView(myFlexbox);

            }
        });

        return builder.create();
    }

    // тащим из Реалма наш текст и отдаём String с текстом тому, кто спросил
    private String getTextFromRealm(){

        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<IncomeText> realmResults = realm.where(IncomeText.class).findAll();
        String textFromRealm = realmResults.get(0).getText();
        realm.commitTransaction();
        return textFromRealm;

    }

}

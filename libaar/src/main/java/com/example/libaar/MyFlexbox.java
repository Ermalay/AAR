package com.example.libaar;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFlexbox extends FlexboxLayout {

    String text;
    Context context;

    // в конструктор приходит контекст и текст
    public MyFlexbox(Context context, String text) {
        super(context);
        this.text = text;
        this.context = context;

        init();
    }

    private void init(){
        // разбираем весь текст на слова по разделителю " "
        String[] arrStrings = text.split(" ");

        // перебираем массив слов
        for (final String word :
                arrStrings) {

            // если слово по всем признакам является email'ом, то
            if (isWordEmail(word)){
                // с помощью класса GravatarChip делаем из него MaterialDesign chip и
                GravatarChip gravatarChip = new GravatarChip(context, word);

                // если надо получить по клику адрес email, то можно сделать это тут
                gravatarChip.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, word, Toast.LENGTH_LONG).show();
                    }
                });

                // добавляем этот chip к view (FlexboxLayout)
                addView(gravatarChip);
            }else {
                // если слово НЕ является email'ом, то просто добавляем TextView
                TextView textView = new TextView(context);
                textView.setText(word + " ");
                addView(textView);
            }
        }

        // Тут расставляем флаги
        // выстраиваем TextView с переносом
        setFlexWrap(FlexboxLayout.FLEX_WRAP_WRAP);
        // выстраиваем TextView от начала экрана
        setAlignContent(FlexboxLayout.ALIGN_CONTENT_FLEX_START);
        // выстраиваем все TextView по ширине экрана
        setJustifyContent(FlexboxLayout.JUSTIFY_CONTENT_SPACE_BETWEEN);
    }

    // проверяем является ли слово e-mail'ом
    private boolean isWordEmail (String string){

        // составляем шаблон адреса email
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");

        // сопоставляем шаблон со входящей строкой
        Matcher matcher = pattern.matcher(string);
        boolean matches = matcher.matches();

        // возвращаем булево
        return matches;
    }
}

package com.example.ermalay.myaar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libaar.EmailTextView;
import com.example.libaar.MyToast;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    String myString = new String("Тут: какое-то, kavkazoff@gmail.com предложение с какими-то kavkazoff@mail.ru имейлами");

    ViewGroup.LayoutParams ll_LayoutParams = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);

    ViewGroup.LayoutParams tv_LayoutParams = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);

    int all_tv_width = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        // разбираем весь текст на слова по пробелам
        String[] arrStrings = myString.split(" ");

        // узнаём размеры экрана
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int myDispayWidth = displayMetrics.widthPixels;

        LinearLayout linearLayoutMain = new LinearLayout(this);
        linearLayoutMain.setOrientation(LinearLayout.VERTICAL);
        linearLayoutMain.setLayoutParams(ll_LayoutParams);

        String justText = "";
        TextView tempTextView = new TextView(this);
        tempTextView.setLayoutParams(tv_LayoutParams);
        tempTextView.setLines(1);


        for (String s :
                arrStrings) {

//            justText += s;
//            tempTextView.setText(justText);
//            tempTextView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//                @Override
//                public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
//                    if (myDispayWidth < (i2 - i)){
//
//                    }
//                }
//            });

            if (checkEmail(s)){

                tempTextView.setText(s);
                tempTextView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                    @Override
                    public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                        int w = i2 - i;
                        Log.d("qqq", String.valueOf(w) + " - ширина TextView");
                    }
                });

                linearLayoutMain.addView(getTextView(justText));
                justText = "";

                linearLayoutMain.addView(backCTV(s));

            } else {
                justText += s + " ";
            }

        }

        // если в строке что-то есть, то вставляем
        if (justText.length() > 0){
            linearLayoutMain.addView(getTextView(justText));
        }

        TextView someTextView = new TextView(this);
        someTextView.setText("другой LL");

        linearLayoutMain.addView(getLayout(someTextView));

        setContentView(linearLayoutMain);
    }

    private TextView getTextView (String string){
        TextView textView = new TextView(this);
        textView.setLayoutParams(tv_LayoutParams);
        textView.setLines(1);
        textView.setText(string);

        return textView;
    }

    private LinearLayout getLayout (TextView textView){




        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(ll_LayoutParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(textView);
        return linearLayout;
    }

    private EmailTextView backCTV(String myString){

        EmailTextView customTextView = new EmailTextView(this);
        customTextView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        customTextView.setText(myString + " ");
        return customTextView;
    }

    private TextView backTV(String myString){
        TextView textView = new TextView(this);
        textView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(myString + " ");

        return textView;
    }

    private boolean checkEmail (String string){

        // составляем шаблон адреса email
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");

        // сопоставляем шаблон со входящей строкой
        Matcher matcher = pattern.matcher(string);
//        boolean matches = matcher.matches();
        boolean matches = matcher.matches();
        return matches;
    }


}

package com.example.ermalay.myaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.libaar.MyFlexbox;

/**
 * Этот класс знает только кто и что будет показывать, а как показывать - знают другие
 */
public class MainActivity extends AppCompatActivity {

    String myString = "Привет, меня зовут Вася Обломов а мой адрес oblomov@mail.ru ! " +
            "тут какой-то длинный kavkazoff@gmail.com текст kavkazoff@gmail.com " +
            "со всякими подробностями и фигулинками и про булочки, " +
            "чай и прочее и kavkazoff@mail.ru что-то ещё всякое разное. " +
            "И дизайнер из меня - барахло! myemailaddress@example.com";

    // класс, наследуемый от FlexboxLayout
    MyFlexbox myFlexbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // передаём текст
        myFlexbox = new MyFlexbox(this, myString);

        setContentView(myFlexbox);
    }
}

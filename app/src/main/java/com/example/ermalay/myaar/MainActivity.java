package com.example.ermalay.myaar;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.libaar.Dialog;
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

        // передаём текст для обработки и отображения
        myFlexbox = new MyFlexbox(this, myString);

        setContentView(myFlexbox);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.view_email:
                showEditDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        Dialog dialog = new Dialog();
        dialog.show(fm, "fragment_edit_email");
    }
}

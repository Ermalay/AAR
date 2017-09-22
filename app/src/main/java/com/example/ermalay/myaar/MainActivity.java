package com.example.ermalay.myaar;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.libaar.ChoiceDialog;
import com.example.libaar.IncomeText;
import com.example.libaar.MyFlexbox;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Этот класс знает только кто и что будет показывать, а как показывать - знают другие
 */
public class MainActivity extends AppCompatActivity {

    String myString = "Привет, меня зовут Вася Обломов а мой адрес oblomov@mail.ru ! " +
            "тут какой-то длинный kavkazoff@gmail.com текст kavkazoff@gmail.com " +
            "со всякими подробностями и фигулинками и про булочки, " +
            "чай и прочее и kavkazoff@mail.ru что-то ещё всякое разное. " +
            "А дизайнер из меня - барахло! myemailaddress@example.com";

    private Realm realm;

    // класс, наследуемый от FlexboxLayout
    MyFlexbox myFlexbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // запишем строку в Realm
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        IncomeText incomeText = realm.createObject(IncomeText.class);
        incomeText.setText(myString);
        realm.commitTransaction();


        // передаём текст для обработки и отображения
        myFlexbox = new MyFlexbox(this, myString);

        setContentView(myFlexbox);
    }

    // не забудем закрыть Realm при дестрое активити
    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.view_choice_email:
                showEditDialogChoiceEmail();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showEditDialogChoiceEmail() {
        FragmentManager fm = getSupportFragmentManager();
        ChoiceDialog choiceDialog = new ChoiceDialog();
        choiceDialog.show(fm, "fragment_edit_choice_email");
    }
}

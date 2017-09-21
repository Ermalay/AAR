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

public class ChoiceDialog extends DialogFragment {

    SharedPreferences sharedPreferences;

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Новое Диалоговое окно");
        builder.setMessage("Для закрытия окна нажмите ОК");
        builder.setIcon(R.drawable.ok24);
        builder.setNegativeButton(R.string.not_show_at, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                sharedPreferences = getContext().getSharedPreferences("my_settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("is_at", false);
                editor.apply();
                editor.commit();

                String myString = "Привет, меня зовут Вася Обломов а мой адрес oblomov@mail.ru ! " +
                        "тут какой-то длинный kavkazoff@gmail.com текст kavkazoff@gmail.com " +
                        "со всякими подробностями и фигулинками и про булочки, " +
                        "чай и прочее и kavkazoff@mail.ru что-то ещё всякое разное. " +
                        "И дизайнер из меня - барахло! myemailaddress@example.com";

                MyFlexbox myFlexbox = new MyFlexbox(getActivity(), myString);
                getActivity().setContentView(myFlexbox);

            }
        });
        builder.setPositiveButton(R.string.show_at, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                sharedPreferences = getContext().getSharedPreferences("my_settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("is_at", true);
                editor.apply();
                editor.commit();

//                Intent intent = new Intent(getActivity(), getActivity().getClass() );
//                getActivity().finish();
//                getActivity().startActivity(intent);

                String myString = "Привет, меня зовут Вася Обломов а мой адрес oblomov@mail.ru ! " +
                        "тут какой-то длинный kavkazoff@gmail.com текст kavkazoff@gmail.com " +
                        "со всякими подробностями и фигулинками и про булочки, " +
                        "чай и прочее и kavkazoff@mail.ru что-то ещё всякое разное. " +
                        "И дизайнер из меня - барахло! myemailaddress@example.com";

                MyFlexbox myFlexbox = new MyFlexbox(getActivity(), myString);
                getActivity().setContentView(myFlexbox);

            }
        });

        return builder.create();
    }

}

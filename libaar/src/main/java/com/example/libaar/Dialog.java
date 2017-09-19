package com.example.libaar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Dialog extends DialogFragment implements CompoundButton.OnCheckedChangeListener {

    private TextView dialog_title;
    private Switch aSwitch;

    SharedPreferences sharedPreferences;

    public Dialog() {
        // нужен пустой конструктор
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dialog, container);
        dialog_title = (TextView) view.findViewById(R.id.dialog_title);
        aSwitch = (Switch) view.findViewById(R.id.my_switch);

        SharedPreferences settings = getActivity().getBaseContext().getSharedPreferences("my_settings", Context.MODE_PRIVATE);
        boolean b = settings.getBoolean("is_at", true);
        aSwitch.setChecked(b);
        if (aSwitch != null){
            aSwitch.setOnCheckedChangeListener(this);
        }

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        sharedPreferences = getContext().getSharedPreferences("my_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_at", b);
        editor.apply();
        editor.commit();

        Toast.makeText(getContext(), "Полный e-mail: " + (b ? "да" : "нет"), Toast.LENGTH_SHORT).show();
    }
}

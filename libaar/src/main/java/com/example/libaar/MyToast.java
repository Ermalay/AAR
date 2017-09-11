package com.example.libaar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ermalay on 08.09.2017.
 */

public class MyToast {

    public static Toast kavka (@NonNull Context context, @NonNull CharSequence message, int duration) {

        //экземпляр Toast
        final Toast currentToast = new Toast(context);
        // экземпляр View
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.toast_layout, null);

        // определяем TextView
        final TextView toastTextView = (TextView) toastLayout.findViewById(R.id.toast_text);
        toastTextView.setText(message + " - !!!");
        toastTextView.setTextColor(Color.parseColor("#FF3535"));

        // к нашему Toast'у присваиваем вьюху и задержку
        currentToast.setView(toastLayout);
        currentToast.setDuration(duration);
        return currentToast;
    }

}

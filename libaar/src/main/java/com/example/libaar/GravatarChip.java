package com.example.libaar;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

// этот класс делает из текста красивый MaterialDesign chip
public class GravatarChip extends LinearLayout {

    String email;
    Context context;

    // в конструктор приходит контекст и текст
    public GravatarChip(Context context, String email) {
        super(context);
        this.context = context;
        this.email = email;

        init();
    }

    private void init(){

        // добавляем к chip красивый фон из ресурсов
        setBackground(getResources().getDrawable(R.drawable.shape_chip_drawable, null));

        // Gravatar сказал, что сам посчитает хэш https://ru.gravatar.com/site/implement/images/java/
        String hash = MD5Util.md5Hex(email);

        // библиотека CircleImageView делает фото в милых кружочках
        CircleImageView imageView = new CircleImageView(context);
        // цвет окантовки фото
        imageView.setBorderColor(Color.BLUE);
        // толщина окантовки фото
        imageView.setBorderWidth(1);

        // Picasso асинхронно тащит из инета фото, кэширует их и показывает
        Picasso.with(context)
                // где берём фото
                .load("https://www.gravatar.com/avatar/" + hash)
                // что показываеем, если не смогли притащить из инета
                .placeholder(R.drawable.ok24)
                // меняем размер фото
                .resize(48, 48)
                // где показываем фото
                .into(imageView);

        // делаем отступ между фото и текстом
        imageView.setPadding(0, 0, 5, 0);
        addView(imageView);

        // добавляем сам текст email'a
        TextView textView = new TextView(context);
        textView.setText(cropString(email));
        textView.setPadding(0, 0, 15, 0);
        addView(textView);
    }

    // тут режем или не режем e-mail (воозвращаем String до @ или полный), если чего, то полный e-mail
    private String cropString (String string){
        SharedPreferences settings = context.getSharedPreferences("my_settings", Context.MODE_PRIVATE);
        boolean b = settings.getBoolean("is_at", true);

        // если в преференсах сидит true, то вернём как есть
        if (b){
            return string;
        } else {

            // если в преференсах сидит false, то вернём e-mail до знака @
            String result = string.substring(0, string.indexOf("@"));
            return result;
        }
    }
}

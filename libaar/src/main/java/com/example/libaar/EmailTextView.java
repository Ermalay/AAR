package com.example.libaar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


public class EmailTextView extends AppCompatTextView {

    public EmailTextView(Context context) {
        super(context);
        init();
    }

    public EmailTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmailTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        setBackground(getResources().getDrawable(R.drawable.shape_chip_drawable, null));
        setLines(1);
    }
}

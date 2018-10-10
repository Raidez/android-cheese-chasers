package com.mga.cheesechasers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Controller extends View implements View.OnTouchListener{
    public Controller(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}

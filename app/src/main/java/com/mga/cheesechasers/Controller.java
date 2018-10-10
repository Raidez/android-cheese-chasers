package com.mga.cheesechasers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Controller implements View.OnTouchListener{
    public Controller(Context context, AttributeSet attrs){
        super();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}

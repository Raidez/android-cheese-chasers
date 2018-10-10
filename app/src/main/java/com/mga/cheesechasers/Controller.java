package com.mga.cheesechasers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Grille grille = new Grille(5,5);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        grille.draw(canvas,paint);
        //this.getWidth();
        //this.getHeight();

    }
}

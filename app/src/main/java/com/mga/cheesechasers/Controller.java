package com.mga.cheesechasers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Controller extends View implements View.OnTouchListener{
    private boolean mBooleanIsPressed = false;

    int dx = 0;
    int dy = 0;
    int predx;
    int predy;

    public Controller(Context context, AttributeSet attrs){
        super(context, attrs);
        this.setOnTouchListener(this);
    }

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        public void run() {
            Log.d(" HANDLER" , "log LONG TOUCH");
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        Log.d(" TOUCH" , "TOUCH");
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                Log.d(" TOUCH" , "DOWN");
                // Execute your Runnable after 1000 milliseconds = 1 second.
                handler.postDelayed(runnable, 1000);
                mBooleanIsPressed = true;

                predx = x;
                predy = y;
                break;
            case MotionEvent.ACTION_UP:
                if(mBooleanIsPressed) {
                    mBooleanIsPressed = false;
                    handler.removeCallbacks(runnable);
                }
                Log.d(" TOUCH" , "UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(" TOUCH" , "MOVE");
                this.dx =  predx - x;
                this.dy =   predy - y;
                break;
        }
        this.invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.cat);


        canvas.drawBitmap(bitmap, 10 - dx, 10 - dy, paint);

    }
}

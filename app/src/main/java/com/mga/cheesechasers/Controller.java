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

import static java.lang.Math.abs;

public class Controller extends View implements View.OnTouchListener{
    private boolean mBooleanIsPressed = false;

    Grille grille;
    int dx = 0;
    int dy = 0;
    int predx;
    int predy;
    int tailleImage;

    public Controller(Context context, AttributeSet attrs){
        super(context, attrs);
        grille = new Grille(10, 10, TypeCarte.SOURIS);;
        tailleImage = 200;
        dx = 20;
        dy = 90;
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
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                Log.d(" TOUCH" , "DOWN");
                // Execute your Runnable after 1000 milliseconds = 1 second.
                handler.postDelayed(runnable, 1000);
                mBooleanIsPressed = true;

                predx = x;
                predy = y;

                Grille.Carte carte = grille.getAt((int) event.getX(), (int) event.getY(), dx, dy, tailleImage);
                if (carte.type == TypeCarte.DISPONIBLE) {
                    grille.setAt(carte.x, carte.y, TypeCarte.FROMAGE);
                }
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
            /*case MotionEvent.ACTION_POINTER_DOWN: case MotionEvent.ACTION_POINTER_UP:
                Log.d("POINTER", "Zoom...zooooom ");
                break;*/
        }
        this.invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Grille.x = Grille.x - dx;
        Grille.y = Grille.y - dy;
        if (Grille.x > 0) Grille.x = 0;
        if (Grille.y > 0) Grille.y = 0;
        //Log.d("WIDTH", "View width : "+this.getWidth()+" | Grille x : "+abs(Grille.x)+" | Grille width : "+(grille.width * tailleImage));
        //Log.d("HEIGHT", "View height : "+this.getHeight()+" | Grille y : "+abs(Grille.y)+" | Grille height : "+(grille.width * tailleImage));
        if (abs(Grille.x) + this.getWidth() > (grille.width * tailleImage)) {
            Grille.x = abs((grille.width * tailleImage) - this.getWidth());
        }
        if (abs(Grille.y) + this.getHeight() > (grille.width * tailleImage)) {
            Grille.y = abs((grille.width * tailleImage) - this.getHeight());
        }


        grille.draw(this, canvas,  Grille.x,  Grille.y, tailleImage);
    }
}

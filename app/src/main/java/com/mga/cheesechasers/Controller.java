package com.mga.cheesechasers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Controller extends View implements View.OnTouchListener{
    Grille grille;
    int tailleImage = 90;
    int dx = 20;
    int dy = 90;

    public Controller(Context context, AttributeSet attrs){
        super(context, attrs);
        grille = new Grille(5, 5, TypeCarte.SOURIS);
        this.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Grille.Carte carte = grille.getAt((int) event.getX(), (int) event.getY(), dx, dy, tailleImage);
            if (carte.type == TypeCarte.DISPONIBLE) {
                grille.setAt(carte.x, carte.y, TypeCarte.FROMAGE);
            }
        }
        this.invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        grille.draw(this, canvas, dx, dy, tailleImage);
    }
}

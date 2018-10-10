package com.mga.cheesechasers;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Grille {
    Carte[][] cartes;
    int width, height;

    Grille(int width, int height) {
        this.width = width;
        this.height = height;
        //this.cartes = new Carte[width][height];
        //this.cartes[(int)(width/2)][(int)(height/2)] = new Carte(TypeCarte.SOURIS, app);
    }

    void draw(Canvas canvas, Paint paint) {
        for (int y = 0; y < this.height; y++) {
            canvas.drawLine(0.0f, (float) y * 10, 100.0f, (float) y * 10, paint);
        }
    }
}

package com.mga.cheesechasers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Type;

public class Grille {
    Carte[][] cartes;
    int width, height;

    Grille(int width, int height) {
        this.width = width;
        this.height = height;
        this.cartes = new Carte[width][height];
    }

    void draw(View view, Canvas canvas, Paint paint) {
        //this.cartes[(int)(width/2)][(int)(height/2)] = new Carte(TypeCarte.SOURIS, view.getResources());

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                canvas.drawBitmap(new Carte(TypeCarte.SOURIS, view.getResources()).image, view.getWidth() / this.width, view.getHeight() / this.height, paint);
            }
        }

        for (int x = 0; x < this.width; x++) {
            int dx = x * view.getWidth() / this.width;
            canvas.drawLine(dx, 0, dx, view.getHeight(), paint);
        }

        for (int y = 0; y < this.height; y++) {
            int dy = y * view.getHeight() / this.height;
            canvas.drawLine(0, dy, view.getWidth(), dy, paint);
        }
    }
}

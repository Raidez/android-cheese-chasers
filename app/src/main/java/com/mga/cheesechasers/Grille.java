package com.mga.cheesechasers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.View;

import java.lang.reflect.Type;

public class Grille {
    TypeCarte[][] cartes;
    int width, height;

    Grille(int width, int height) {
        this.width = width;
        this.height = height;
        this.cartes = new TypeCarte[width][];
        for (int x = 0; x < width; x++) {
            this.cartes[x] = new TypeCarte[height];
            for (int y = 0; y < height; y++) {
                this.cartes[x][y] = TypeCarte.VIDE;
            }
        }
    }

    void draw(View view, Canvas canvas, int dx, int dy, int tailleImage) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2f);

        int maxWidth = view.getWidth();
        int maxHeight = view.getHeight();

        // affichage de la carte correspondante
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                int leftPos = dx + x * tailleImage;
                int topPos = dy + y * tailleImage;
                Rect position = new Rect(leftPos, topPos, leftPos+tailleImage, topPos+tailleImage);
                Bitmap image = genererImage(view.getResources(), this.cartes[x][y]);
                if (image != null) {
                    canvas.drawBitmap(image, null, position, paint);
                }
            }
        }

        // affichage des lignes
        for (int x = 0; x <= this.width; x++) {
            int startStopX = dx + x * tailleImage;
            int stopY = dy + this.height * tailleImage;
            canvas.drawLine(startStopX, dy, startStopX, stopY, paint);

            for (int y = 0; y <= this.height; y++) {
                int startStopY = dy + y * tailleImage;
                int stopX = dx + this.width * tailleImage;
                canvas.drawLine(dx, startStopY, stopX, startStopY, paint);
            }
        }
    }

    Bitmap genererImage(Resources res, TypeCarte type) {
        Bitmap image = null;
        switch (type) {
            case SOURIS:
                image = BitmapFactory.decodeResource(res, R.drawable.mouse);
                break;
            case CHAT:
                image = BitmapFactory.decodeResource(res, R.drawable.cat);
                break;
            case FROMAGE:
                image = BitmapFactory.decodeResource(res, R.drawable.cheese);
                break;
            case PIEGE:
                image = BitmapFactory.decodeResource(res, R.drawable.mousetrap);
                break;
        }

        return image;
    }
}

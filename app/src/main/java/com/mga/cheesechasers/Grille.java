package com.mga.cheesechasers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Type;

public class Grille {
    TypeCarte[][] cartes;
    int width, height;

    Grille(int width, int height, TypeCarte carteInitiale) {
        this.width = width;
        this.height = height;
        this.cartes = new TypeCarte[width][];
        for (int x = 0; x < width; x++) {
            this.cartes[x] = new TypeCarte[height];
            for (int y = 0; y < height; y++) {
                this.cartes[x][y] = TypeCarte.VIDE;
            }
        }

        // génération des cartes de base
        int x = (int) Math.floor(width / 2);
        int y = (int) Math.floor(height / 2);
        for (int dx = (x - 1); dx <= (x + 1); dx++) {
            for (int dy = (y - 1); dy <= (y + 1); dy++) {
                this.cartes[dx][dy] = TypeCarte.DISPONIBLE;
            }
        }
        this.cartes[x][y] = carteInitiale;
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

    public static class Carte {
        int x;
        int y;
        TypeCarte type;
    }

    Carte getAt(int x, int y, int dx, int dy, int tailleImage) {
        int tx = (x - dx) / tailleImage;
        int ty = (y - dy) / tailleImage;

        Carte carte = new Carte();
        carte.x = tx;
        carte.y = ty;
        carte.type = this.cartes[tx][ty];

        return carte;
    }

    void setAt(int x , int y, TypeCarte carte) {
        this.cartes[x][y] = carte;
    }

    Bitmap genererImage(Resources res, TypeCarte type) {
        Bitmap image = null;
        switch (type) {
            case DISPONIBLE:
                image = BitmapFactory.decodeResource(res, R.drawable.plus);
                break;
            case SOURIS:
                image = BitmapFactory.decodeResource(res, R.drawable.mouse);
                break;
            case SOURIS_INACTIVE:
                image = BitmapFactory.decodeResource(res, R.drawable.mouseinvalidated);
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
            case PIEGE_INACTIF:
                image = BitmapFactory.decodeResource(res, R.drawable.mousetrapinvalidated);
                break;
        }

        return image;
    }
}

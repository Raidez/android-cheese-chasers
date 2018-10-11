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
    static int x = 0, y = 0;

    /**
     * Construit une tableau pour représenter la grille et les cartes
     * @param width longueur de la grille
     * @param height largeur de la grille
     * @param carteInitiale carte centrée au milieu de la grille avec 8 cases disponibles autour
     */
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
        this.setAt(x, y, carteInitiale);
    }

    /**
     * Dessine la grille et les cartes à la position dx, dy
     * @param view
     * @param canvas
     * @param dx position du tableau sur l'écran
     * @param dy position du tableau sur l'écran
     * @param tailleImage taille en pixel d'une case
     */
    void draw(View view, Canvas canvas, int dx, int dy, int tailleImage) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2f);

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

    /**
     * Récupère le type de la case sur l'écran
     * @param x
     * @param y
     * @param dx
     * @param dy
     * @param tailleImage
     * @return
     */
    Carte getAt(int x, int y, int dx, int dy, int tailleImage) {
        int tx = (x - dx) / tailleImage;
        int ty = (y - dy) / tailleImage;

        Carte carte = new Carte();
        carte.x = tx;
        carte.y = ty;
        carte.type = this.cartes[tx][ty];

        return carte;
    }

    /**
     * Modifie une carte à une position tout en gérant l'ensemble des règles de gestion du jeu
     * @param x
     * @param y
     * @param carte
     */
    void setAt(int x , int y, TypeCarte carte) {
        // enlever toutes les cases disponibles
        for (int dx = 0; dx < this.width; dx++) {
            for (int dy = 0; dy < this.height; dy++) {
                TypeCarte c = this.cartes[dx][dy];
                if (c == TypeCarte.DISPONIBLE) {
                    this.cartes[dx][dy] = TypeCarte.VIDE;
                }
            }
        }

        // ajouter les nouvelles cases disponibles
        for (int dx = (x - 1); dx <= (x + 1); dx++) {
            for (int dy = (y - 1); dy <= (y + 1); dy++) {
                if (dx >= 0 && dy >= 0 && dx < this.width && dy < this.height) {
                    TypeCarte c = this.cartes[dx][dy];
                    if (c == TypeCarte.VIDE) {
                        this.cartes[dx][dy] = TypeCarte.DISPONIBLE;
                    }
                }
            }
        }
        this.cartes[x][y] = carte;
    }

    /**
     * gestion de la logique du jeu
     */
    void gestionLogique() {
        // les souris autour d'un chat sont désactivé
        
    }

    /**
     * Récupère l'image selon le type de la carte
     * @param res
     * @param type
     * @return
     */
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

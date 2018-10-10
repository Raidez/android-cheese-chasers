package com.mga.cheesechasers;

import android.app.Activity;

public class Grille {
    Carte[][] cartes;
    int width, height;

    Grille(Activity app, int width, int height) {
        this.width = width;
        this.height = height;
        this.cartes = new Carte[width][height];

        this.cartes[(int)(width/2)][(int)(height/2)] = new Carte(TypeCarte.SOURIS, app);
    }
}

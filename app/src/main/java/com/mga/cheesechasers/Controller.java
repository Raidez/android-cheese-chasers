package com.mga.cheesechasers;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Controller extends View implements View.OnTouchListener{
    Pile pile;
    Grille grille;
    TypeCarte prochaineCarte;
    int tailleImage = 90;
    int dx = 0;
    int dy = 0;
    ImageView image;

    public Controller(Context context, AttributeSet attrs){
        super(context, attrs);
        this.setOnTouchListener(this);

        pile = new Pile();
        grille = new Grille(5, 5, pile.pioche());
        prochaineCarte = pile.pioche();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Grille.Carte carte = grille.getAt((int) event.getX(), (int) event.getY(), dx, dy, tailleImage);
            if (carte.type == TypeCarte.DISPONIBLE) {
                TypeCarte actuelle = prochaineCarte;
                grille.setAt(carte.x, carte.y, actuelle);
                prochaineCarte = pile.pioche();
                grille.gestionLogique();
            }
        }
        this.invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        grille.draw(this, canvas, dx, dy, tailleImage);
        image.setImageBitmap(grille.genererImage(this.getResources(), prochaineCarte));
    }
}

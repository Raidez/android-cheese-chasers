package com.mga.cheesechasers;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Carte {
    TypeCarte type;
    Bitmap image;

    Carte(TypeCarte type, Resources res) {
        this.type = type;
        switch (type) {
            case SOURIS:
                this.image = BitmapFactory.decodeResource(res, R.drawable.mouse);
                break;
            case CHAT:
                this.image = BitmapFactory.decodeResource(res, R.drawable.cat);
                break;
            case FROMAGE:
                this.image = BitmapFactory.decodeResource(res, R.drawable.cheese);
                break;
            case PIEGE:
                this.image = BitmapFactory.decodeResource(res, R.drawable.mousetrap);
                break;
        }
    }
}

package com.mga.cheesechasers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Pile {
    List<TypeCarte> cartes;

    public Pile() {
        int nbsouris=20;
        int nbfromage=9;
        int nbchat=7;
        int nbpiege=4;

        cartes = new ArrayList<>();
        for(int i=0; i< 40; i++){
            if(nbsouris>0){
                cartes.add(TypeCarte.SOURIS);
                nbsouris--;
            }
            else if(nbfromage>0){
                cartes.add(TypeCarte.FROMAGE);
                nbfromage--;
            }
            else if(nbchat>0){
                cartes.add(TypeCarte.CHAT);
                nbchat--;
            }
            else if(nbpiege>0){
                cartes.add(TypeCarte.PIEGE);
                nbpiege--;
            }
        }
        Collections.shuffle(cartes);
    }

    //Fonction pioche
    public TypeCarte pioche(){

        //récup dernière carte
        TypeCarte dernierecarte = cartes.get(cartes.size()-1);

        //suppression dernière carte tableau
        cartes.remove(cartes.size()-1);

        return dernierecarte;

    }






}

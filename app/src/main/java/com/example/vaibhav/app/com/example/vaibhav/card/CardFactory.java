package com.example.vaibhav.app.com.example.vaibhav.card;

/**
 * Created by Vaibhav on 18-11-2016.
 */

public class CardFactory {
    public static Card  getCard(String cradType) {
        if(cradType.equalsIgnoreCase("ONLY_TITLE")){
            return new ONLYTITLE();
        }else{
            return new Card();

        }

    }

}

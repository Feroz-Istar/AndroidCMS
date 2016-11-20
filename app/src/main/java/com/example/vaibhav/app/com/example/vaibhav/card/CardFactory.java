package com.example.vaibhav.app.com.example.vaibhav.card;

/**
 * Created by Vaibhav on 18-11-2016.
 */

public class CardFactory {
    public static Card  getCard(String cradType) {
        if(cradType.equalsIgnoreCase("ONLY_TITLE")){
            return new ONLYTITLE();
        }else if(cradType.equalsIgnoreCase("ONLY_TITLE_IMAGE")){
            return new ONLYTITLEIMAGE();
        }
        else if(cradType.equalsIgnoreCase("ONLY_TITLE_LIST")){
            return new ONLYTITLELIST();
        }
        //
        else{
            return new Card();

        }

    }

}

package com.example.vaibhav.app.com.example.vaibhav.card;

import com.example.vaibhav.app.com.example.vaibhav.card.adapter.DummyFragment;

/**
 * Created by Vaibhav on 18-11-2016.
 */

public class CardFactory {
    public static Card  getCard(String cradType) {
        if(cradType != null) {

            if (cradType.equalsIgnoreCase("ONLY_TITLE")) {
                return new ONLYTITLE();
            } else if (cradType.equalsIgnoreCase("ONLY_TITLE_IMAGE")) {
                return new ONLYTITLEIMAGE();
            } else if (cradType.equalsIgnoreCase("ONLY_TITLE_LIST")) {
                return new ONLYTITLELISTRECYCYLE();
            } else if (cradType.equalsIgnoreCase("ONLY_TITLE_IMAGE")) {
                return new ONLY_TITLE_IMAGE();
            } else if (cradType.equalsIgnoreCase("ONLY_TITLE_PARAGRAPH")) {
                return new ONLY_TITLE_PARAGRAPH();
            } else if (cradType.equalsIgnoreCase("ONLY_TITLE_PARAGRAPH_IMAGE")) {
                return new ONLY_TITLE_PARAGRAPH_IMAGE();
            }
            //ONLY_TITLE_PARAGRAPH_IMAGE
            else {
                return new DummyFragment();

            }
        }else {
            return new DummyFragment();

        }

    }

}

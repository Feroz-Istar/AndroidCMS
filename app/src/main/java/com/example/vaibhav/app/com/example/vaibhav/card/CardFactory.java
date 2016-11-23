package com.example.vaibhav.app.com.example.vaibhav.card;

import com.example.vaibhav.app.com.example.vaibhav.card.adapter.DummyFragment;

/**
 * Created by Vaibhav on 18-11-2016.
 */

public class CardFactory {
    public static Card getCard(String cradType) {
        if (cradType != null) {

            if (cradType.equalsIgnoreCase("ONLY_TITLE")) {
                return new ONLYTITLE();
            } else if (cradType.equalsIgnoreCase("ONLY_TITLE_IMAGE")) {
                return new ONLYTITLEIMAGE();
            }else if (cradType.equalsIgnoreCase("ONLY_TITLE_PARAGRAPH_cells_fragemented")) {
                return new ONLY_TITLE_PARAGRAPH_cells_fragemented();
            }else if (cradType.equalsIgnoreCase("ONLY_2BOX")) {
                return new ONLY_2BOX();
            } else if (cradType.equalsIgnoreCase("ONLY_TITLE_LIST")) {
                return new ONLYTITLELIST();
            }  else if (cradType.equalsIgnoreCase("ONLY_LIST")) {
                return new ONLYLIST();
            }  else if (cradType.equalsIgnoreCase("ONLY_TITLE_LIST_NUMBERED")) {
                return new ONLYTITLELIST();
            }else if (cradType.equalsIgnoreCase("ONLY_PARAGRAPH_IMAGE")) {
                return new ONLYPARAGRAPHIMAGE();
            }  else if (cradType.equalsIgnoreCase("ONLY_PARAGRAPH")) {
                return new ONLY_PARAGRAPH();
            }  else if (cradType.equalsIgnoreCase("ONLY_2TITLE")) {
                return new ONLY_2TITLE();
            }else if (cradType.equalsIgnoreCase("ONLY_2TITLE_IMAGE")) {
                return new ONLY_2TITLE_IMAGE();
            }else if (cradType.equalsIgnoreCase("ONLY_PARAGRAPH_TITLE")) {
                return new ONLY_PARAGRAPH_TITLE();
            }  else if (cradType.equalsIgnoreCase("ONLY_LIST_NUMBERED")) {
                return new ONLYLIST();
            } else if (cradType.equalsIgnoreCase("ONLY_TITLE_TREE")) {
                return new ONLYTITLETREE();
            }else if (cradType.equalsIgnoreCase("ONLY_TITLE_IMAGE")) {
                return new ONLY_TITLE_IMAGE();
            } else if (cradType.equalsIgnoreCase("ONLY_TITLE_PARAGRAPH")) {
                return new ONLY_TITLE_PARAGRAPH();
            } else if (cradType.equalsIgnoreCase("ONLY_TITLE_PARAGRAPH_IMAGE")) {
                return new ONLY_TITLE_PARAGRAPH_IMAGE();
            } else if (cradType.equalsIgnoreCase("ONLY_VIDEO")) {
                return new ONLY_VIDEO();
            }else if (cradType.equalsIgnoreCase("NO_CONTENT")) {
                return new NO_CONTENT();
            }
            //
            else {
                return new DummyFragment();

            }
        } else {
            return new DummyFragment();

        }

    }

}

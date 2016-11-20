package com.example.vaibhav.app.cmspojo;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

/**
 * Created by Vaibhav on 18-11-2016.
 */

public class Helper  {

    public static void main(String[] args) {
        Serializer serializer = new Persister();
        File source = null;

        try {
            File file = new File("C:\\Users\\Vaibhav\\AndroidStudioProjects\\app\\app\\src\\androidTest\\java\\example.xml");
            //source =  getAssets().open("example.xml");
            CMSPresentation example = serializer.read(CMSPresentation.class, file);
            for(CMSSlide cmsSlide:example.getSlides()){
                System.out.println(cmsSlide.getTitle().getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

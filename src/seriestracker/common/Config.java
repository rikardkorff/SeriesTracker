package seriestracker.common;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by rkorff on 2015-07-12.
 */
public class Config {
    private static final String BUNDLE_NAME = "seriestracker.config";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    public Config(){

    }

    public static String getString(String key){
        try{
            return RESOURCE_BUNDLE.getString(key);
        }catch (MissingResourceException e){
            return "!" + key + "!";
        }
    }
}

package com.shoppingcart.util;

import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Set;

/**
 * @author Aditya
 * ReadTaxRates
 */

public class ReadTaxRates {
   
    public static Logger logger = Logger.getLogger(ReadTaxRates.class.getName());
    HashMap<String, Float> hmPrice = new HashMap<String, Float>();
    

    public ReadTaxRates() {        
        readProperties();
    }


    public HashMap<String, Float> readProperties() {
        //Properties prop = new Properties();
        logger.info("entering the method readProperties");


        try {
             File fObj = new File("tax.properties");            
             FileInputStream fis = new FileInputStream(fObj);
             Properties prop = new Properties();
             prop.load(fis);


            for (String key : prop.stringPropertyNames()) {
                String value = prop.getProperty(key);                
                hmPrice.put(key, Float.parseFloat(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("exiting the method readProperties");

        return hmPrice;
    }

    public Set<String> getAllTaxTypes(){
        return hmPrice.keySet();
    }

    public Float getTaxRate(String key) {
        return Float.valueOf(hmPrice.get(key));
    }

}

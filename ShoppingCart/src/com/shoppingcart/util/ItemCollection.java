package com.shoppingcart.util;

import java.util.Hashtable;
import java.util.Set;

/**
 * @author Aditya
 * ItemCollection
 */

public class ItemCollection {

    public static Hashtable<String, Float> htTax = new Hashtable<String, Float>();
    public static Hashtable<String, String> htItems = new Hashtable<String, String>();

    public static final ReadTaxRates readProp = new ReadTaxRates();

    Set<String> taxTypes = readProp.getAllTaxTypes();
    

    public static final float BASIC_TAX = readProp.getTaxRate("BASIC_TAX");
    public static final float IMPORT_TAX = readProp.getTaxRate("IMPORT_TAX");
    public static final float ROUND_UPTO = readProp.getTaxRate("ROUND_UPTO");
    public static final float LUXURY_TAX = readProp.getTaxRate("LUXURY");

    public static final String IMPORT_STRING = "imported";
    public static final String LUXURY_STRING = "luxury";
    public static final String EXCLUDE = "at";


}
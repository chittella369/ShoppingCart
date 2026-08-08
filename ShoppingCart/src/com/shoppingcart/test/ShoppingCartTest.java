package com.shoppingcart.test;

import junit.framework.TestCase;
import com.shoppingcart.util.ShoppingCartException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.util.Set;
import java.util.Iterator;
import java.util.List;

import com.shoppingcart.billing.BillingImpl;
import com.shoppingcart.util.ItemCollection;
import com.shoppingcart.item.ShoppedItem;



/**
 * @author Aditya
 * ShoppingCartTest
 */


public class ShoppingCartTest extends TestCase {

    BillingImpl billing = new BillingImpl();
    public static Logger logger = Logger.getLogger(ShoppingCartTest.class.getName());

    public void setUp() throws Exception {
        super.setUp();
        logger.setLevel(Level.INFO);

        Properties prop = new Properties();
        File f = new File("shoppingcart.properties");
        prop.load(new FileInputStream(f));

        Set<Object> set = prop.keySet();
        Iterator<Object> it = set.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = prop.getProperty(key);
            System.setProperty(key, value);

            ItemCollection.htItems.put(key, value);
        }
    }
    
    private void assertItemCost(ShoppedItem item, float expectedCost) {
        assertEquals(item.getTotalCost(), expectedCost, 0.01);
    }


    private void assertItemTax(ShoppedItem item, float expectedTax) {
        assertEquals(item.getTotalTax(), expectedTax, 0.01);
    }

    public void testInputOne() throws ShoppingCartException {
        logger.log(logger.getLevel(), "entering the method testInputOne",logger.getClass());
        String[] itemList = new String[3];
        itemList[0] = "1 book at 12.49";
        itemList[1] = "1 music CD at 14.99";
        itemList[2] = "1 chocolate bar at 0.85";        

        List<ShoppedItem> list = billing.parseAndCalculate(itemList);
        assertItemCost(list.get(0), 13.74f);
        assertItemCost(list.get(1), 16.49f);
        assertItemCost(list.get(2), 0.94f);
        

        assertItemTax(list.get(0), 1.249f);
        assertItemTax(list.get(1), 1.50f);
        assertItemTax(list.get(2), 0.085f);
        

        assertEquals(billing.totalTax(list), 2.83f, 0.01);
        assertEquals(billing.totalCost(list), 31.17f, 0.01);
        billing.print(list, System.out);
        logger.log(logger.getLevel(), "exiting the method testInputOne",logger.getClass());
    }


    public void testInputTwo() throws ShoppingCartException {
        logger.log(logger.getLevel(), "entering the method testInputOne",logger.getClass());
        String[] itemList = new String[2];
        itemList[0] = "1 imported box of chocolates at 15";
        itemList[1] = "1 luxury watch at 500";
       
        List<ShoppedItem> list = billing.parseAndCalculate(itemList);
        assertItemCost(list.get(0), 17.25f);
        assertItemCost(list.get(1), 625f);
        
        assertItemTax(list.get(0), 2.25f);
        assertItemTax(list.get(1), 125f);

        assertEquals(billing.totalTax(list), 127.25f, 0.01);
        assertEquals(billing.totalCost(list), 642.25f, 0.01);
        billing.print(list, System.out);
        logger.log(logger.getLevel(), "exiting the method testInputOne",logger.getClass());
    }
    
}

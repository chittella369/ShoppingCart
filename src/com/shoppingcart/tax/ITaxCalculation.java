package com.shoppingcart.tax;

import com.shoppingcart.item.ShoppedItem;


/**
 * @author Aditya
 * ITaxCalc
 */
public interface ITaxCalculation {
    public void calculateTotalTax(ShoppedItem item);
}

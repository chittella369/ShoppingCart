package com.shoppingcart.billing;

import java.io.PrintStream;
import java.util.List;

import com.shoppingcart.item.ShoppedItem;
import com.shoppingcart.util.ShoppingCartException;

/**
 * @author Aditya
 * IBilling
 */

public interface IBilling {
    List<ShoppedItem> parseAndCalculate(String itemList[]) throws ShoppingCartException, NumberFormatException;

	void print(List<ShoppedItem> list, PrintStream stream);

	float totalTax(List<ShoppedItem> list);

	float totalCost(List<ShoppedItem> list);

    
}

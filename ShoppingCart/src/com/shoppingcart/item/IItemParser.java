package com.shoppingcart.item;

import com.shoppingcart.util.ShoppingCartException;

/**
 * 
 * @author Aditya
 *
 */

public interface IItemParser {
	ShoppedItem parse(String stItem) throws ShoppingCartException, NumberFormatException;
	
}

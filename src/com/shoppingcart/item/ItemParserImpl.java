package com.shoppingcart.item;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import com.shoppingcart.util.ShoppingCartException;
import com.shoppingcart.util.ItemCollection;


/**
 * 
 * @author Aditya
 *
 */
public class ItemParserImpl implements IItemParser {
	
	ShoppedItem item = null;
	private static ItemParserImpl parser ;
	private boolean blBreak = false;
	
	public static Logger logger = Logger.getLogger(ItemParserImpl.class.getName());
	


	public ItemParserImpl(ShoppedItem item) {
		this.item = item;		
	}

	private ItemParserImpl() {
	}
	
	public static ItemParserImpl getInstance(){
		if(parser == null){
			parser = new ItemParserImpl();
		}
		return parser;
	}

	@Override
	public ShoppedItem parse(String itemStr) throws ShoppingCartException,
			NumberFormatException {
		logger.info("entering the method parse");
		
		ShoppedItem item = new ShoppedItem();
		StringTokenizer token = new StringTokenizer(itemStr, " ");

		Enumeration<String> set = ItemCollection.htItems.keys();

		// Item Quantity
		if (token.hasMoreElements()) {
			int inQuantity = Integer.parseInt((String) token.nextElement());
			item.setQuantity(inQuantity);
		}else
			throw new ShoppingCartException("Invalid Input");

		// Item Name
		int inCount = token.countTokens();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < inCount - 1; i++) {
			sb.append(token.nextToken());
			sb.append(" ");
		}
		String stItemName = sb.toString();
		

		while (set.hasMoreElements()) {
			String stKey = set.nextElement();
			String stItems = ItemCollection.htItems.get(stKey);
			String[] stExemptedItems = stItems.split(",");

			for (int j = 0; j < stExemptedItems.length; j++) {
				
				if (stItemName.toUpperCase().contains(
						stExemptedItems[j].toUpperCase())) {
					item.setTaxExempted(true);
					blBreak = true;
					break;
				} else {
					blBreak = false;
				}
			}
			if (blBreak)
				break;
		}
		item.setTaxExempted(blBreak);	
		item.setItemName(stItemName);

		// Check if item is Imported
		 if (stItemName.toUpperCase().contains(
		 		ItemCollection.IMPORT_STRING.toUpperCase())) {
		 	item.setImported(true);
		 } else {
		 	item.setImported(false);
		 }

		 // Check if item is Luxury
		 if(stItemName.toUpperCase().contains(ItemCollection.LUXURY_STRING.toUpperCase())){
			item.setLuxury(true);
		 }else{
			item.setLuxury(false);
		 }

		// Item Price
		if (token.hasMoreTokens()) {
			String stAmount = token.nextToken();
			float flPrice = Float.parseFloat(stAmount);
			item.setUnitPrice(flPrice);
		}else
			throw new ShoppingCartException("Invalid Input");
		logger.info("exiting the method parse");
		return item;
	}

}

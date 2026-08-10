package com.shoppingcart.billing;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.shoppingcart.item.ItemParserImpl;
import com.shoppingcart.item.ShoppedItem;
import com.shoppingcart.util.ShoppingCartException;
import com.shoppingcart.tax.ITaxCalculation;
import com.shoppingcart.tax.TaxCalculationImpl;


/**
 * @author Aditya
 * BillingImpl
 */

public class BillingImpl implements IBilling {
    ITaxCalculation taxCalc = new TaxCalculationImpl();
	ItemParserImpl parser = null;
	private static NumberFormat NUMBER_FORMAT = new DecimalFormat("#0.00");
	
	public static Logger logger = Logger.getLogger(ItemParserImpl.class.getName());
	
	public BillingImpl(){		
	}

	public List<ShoppedItem> parseAndCalculate(String[] itemList)
			throws ShoppingCartException, NumberFormatException {
		logger.info("entering the method parseAndCalc");
	
		List<ShoppedItem> listOfItems = new ArrayList<ShoppedItem>();

		for (String stItem : itemList) {
			parser = ItemParserImpl.getInstance();
			ShoppedItem item = parser.parse(stItem);
			
			taxCalc.calculateTotalTax(item);
			listOfItems.add(item);
		}
		logger.info("Exiting the method parseAndCalc");
		return listOfItems;
	}

	@Override
	public void print(List<ShoppedItem> list, PrintStream stream) {
		logger.info("entering the method print");
		StringBuffer sb = new StringBuffer();

		if (list == null || list.size() == 0)
			return;

		sb.append("Tax : ");
		sb.append("\n");
		for (ShoppedItem item : list) {
			sb.append("-");
			sb.append(item.getQuantity());
			sb.append(" ");
			
			sb.append(item.getItemName().replaceAll("\\s+at\\s*$", " "));
			sb.append(": ");

			sb.append(NUMBER_FORMAT.format(item.getTotalTax()));			
			sb.append("\n");
		}
		sb.append("\n");
		sb.append("Total Cost with Tax : ");
		sb.append("\n");

		for (ShoppedItem item : list) {
			sb.append("-");
			sb.append(item.getQuantity());
			sb.append(" ");

			sb.append(item.getItemName().replaceAll("\\s+at\\s*$", " "));
			sb.append(": ");

			sb.append(NUMBER_FORMAT.format(item.getTotalCost()));			
			sb.append("\n");
		}
		sb.append("\n");
		sb.append("Total Tax : ");
		sb.append(NUMBER_FORMAT.format(totalTax(list)));
		sb.append("\n");
		sb.append("Total Cost : ");
		sb.append(NUMBER_FORMAT.format(totalCost(list)));
		sb.append("\n");
		stream.println(sb.toString());
		
		stream.flush();
		logger.info("exiting the method print");
	}

	@Override
	public float totalTax(List<ShoppedItem> list) {
		logger.info("entering the method totalTax");
		float flTotalTax = 0.0f;
		for (ShoppedItem itemlist : list) {
			flTotalTax += itemlist.getTotalTax();
		}
		logger.info("exiting the method totalTax");
		return flTotalTax;
	}

	@Override
	public float totalCost(List<ShoppedItem> list) {
		logger.info("entering the method totalCost");
		float flTotalTax = 0.0f;
		for (ShoppedItem itemlist : list) {
			flTotalTax += itemlist.getTotalCost();
		}
		logger.info("entering the method totalCost");
		return flTotalTax;
	}

    
}

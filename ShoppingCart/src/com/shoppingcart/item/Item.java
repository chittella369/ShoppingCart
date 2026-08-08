package com.shoppingcart.item;

import java.util.Enumeration;
import com.shoppingcart.util.ItemCollection;

/**
 * @author Aditya
 * Item
 */

public abstract class Item {
    private String stItemName;
	private float flItemPrice;

	private boolean isImported;
	private float flUnitPrice;

	private boolean isLuxury;


	private boolean blTaxExempted;

	Enumeration<String> set = ItemCollection.htItems.keys();

	public void setItemPrice(float flItemPrice) {

		this.flItemPrice = flItemPrice;
	}

	public float getItemPrice() {

		return flItemPrice;
	}

	public String getItemName() {

		return this.stItemName;
	}

	public void setItemName(String stItemName) {

		this.stItemName = stItemName;

	}

	public boolean isImported() {
		return isImported;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}


	public boolean isLuxury() {
		return isLuxury;
	}

	public void setLuxury(boolean isLuxury) {
		this.isLuxury = isLuxury;
	}
	public void setUnitPrice(float flUnitPrice) {
		this.flUnitPrice = flUnitPrice;

	}

	public float getUnitPrice() {
		return flUnitPrice;
	}

	public boolean isTaxExempted() {
		return blTaxExempted;
	}

	public void setTaxExempted(boolean blTaxExempted) {		
		this.blTaxExempted = blTaxExempted;
	}

	public boolean isBasicTaxExempted(String stItem) {
		while (set.hasMoreElements()) {
			String stKey = set.nextElement();
			String stItems = ItemCollection.htItems.get(stKey);
			System.out.println("***Items : "+stItems);
			String[] stExemptedItems = stItems.split(",");
			for (int j = 0; j < stExemptedItems.length; j++) {
				if (stItem.toUpperCase().contains(
						stExemptedItems[j].toUpperCase())) {
					return true;
				}
			}
		}
		return false;
	}
}

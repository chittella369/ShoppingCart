package com.shoppingcart.item;
/**
 * 
 * @author Aditya
 *
 */
public class ShoppedItem extends Item {
	private int inQuantity;
	private float flTotalCost;
	private float flBasicTax;
	private float flImportTax; 
	private float flLuxuryTax;

	public ShoppedItem() {
		super();
	}

	public void setBasicTax(float flBasicTax) {
		this.flBasicTax = flBasicTax;

	}

	public float getBasicTax() {

		return flBasicTax;
	}

	public void setImportTax(float flImportTax) {
		this.flImportTax = flImportTax;

	}

	public float getImportTax() {
		return flImportTax;
	}


	public void setLuxuryTax(float flLuxuryTax) {
		this.flLuxuryTax = flLuxuryTax;

	}

	public float getLuxuryTax() {

		return flLuxuryTax;
	}

	public float getTotalTax() {
		return flBasicTax + flImportTax + flLuxuryTax;

	}

	public void setTotalCost(float flTotalCost) {
		this.flTotalCost = flTotalCost;

	}

	public float getTotalCost() {

		return flTotalCost;
	}

	public int getQuantity() {
		return inQuantity;
	}

	public void setQuantity(int inQuantity) {
		this.inQuantity = inQuantity;
	}
}


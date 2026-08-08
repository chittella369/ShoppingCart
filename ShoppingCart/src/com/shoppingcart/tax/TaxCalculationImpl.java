package com.shoppingcart.tax;

/**
 * @author Aditya
 * TaxCalculationImpl
 */

import com.shoppingcart.item.ItemParserImpl;
import com.shoppingcart.item.ShoppedItem;
import com.shoppingcart.util.ItemCollection;

import java.util.logging.Logger;

public class TaxCalculationImpl implements ITaxCalculation {

    private float flRoundUpto;
	private float flBasicTax;
	private float flImportTax;
    private float flLuxuryTax;
	public static Logger logger = Logger.getLogger(ItemParserImpl.class.getName());   
	

    public TaxCalculationImpl() {
        this.flBasicTax = ItemCollection.BASIC_TAX;
        this.flImportTax = ItemCollection.IMPORT_TAX;
        this.flLuxuryTax = ItemCollection.LUXURY_TAX;
        this.flRoundUpto = ItemCollection.ROUND_UPTO;
        
    }


    @Override
    public void calculateTotalTax(ShoppedItem item) {
        logger.info("entering the method calculateTotalTax");
        float flCost = item.getUnitPrice() * item.getQuantity();
		item.setItemPrice(flCost);
        
        if (!item.isTaxExempted()) {
            float flTax = item.getUnitPrice() * item.getQuantity() * flBasicTax;
            item.setBasicTax(flTax);
        }
        if (item.isImported()) {
            float flTax = item.getUnitPrice() * item.getQuantity() * flImportTax;
            item.setImportTax(flTax);
        }
        
        if(item.isLuxury()){
            float flTax = item.getUnitPrice() * item.getQuantity() * flLuxuryTax;
            item.setLuxuryTax(flTax);
        }

        item.setTotalCost(item.getItemPrice() + item.getBasicTax() 
				+ item.getImportTax() + item.getLuxuryTax());

        logger.info("exiting the method calculateTotalTax");
    }

    public float getFlRoundUpto(float amount) {
        float roundedValue = (float) (Math.ceil(amount * flRoundUpto) * flRoundUpto);
        return roundedValue;
    }    
}

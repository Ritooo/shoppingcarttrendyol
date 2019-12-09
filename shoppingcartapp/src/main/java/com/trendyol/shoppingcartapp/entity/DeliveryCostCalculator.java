package com.trendyol.shoppingcartapp.entity;

import com.trendyol.shoppingcartapp.entity.interfaces.IDeliveryCostCalculator;


public class DeliveryCostCalculator implements IDeliveryCostCalculator {

    double costPerDelivery;
    double costPerProduct;
    double fixedCost;

    public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    public double getCostPerDelivery() {
        return costPerDelivery;
    }

    public void setCostPerDelivery(double costPerDelivery) {
        this.costPerDelivery = costPerDelivery;
    }

    public double getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(double costPerProduct) {
        this.costPerProduct = costPerProduct;
    }

    public double getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(double fixedCost) {
        this.fixedCost = fixedCost;
    }



    /**
     * @param cart Shopping Cart nesnemizi bu methoda gönderip teslimat maliyetini hesaplıyoruz.
     */
    @Override
    public double calculateFor(ShoppingCart cart) {
        double numberOfDeliveries = cart.getShoppingCartItems().stream().count();
        double numberOfProduct = cart.getNUmberOfProducts();
        double v = ((this.getCostPerDelivery() * numberOfDeliveries) + (this.getCostPerProduct() * numberOfProduct)) + this.getFixedCost();
        cart.setDeliveryCost(v);
        return v;
    }

}

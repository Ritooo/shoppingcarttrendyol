package com.trendyol.shoppingcartapp.entity;

public class ShoppingCartItem {
    Product product;
    int unit;

    public ShoppingCartItem(Product product, int unit) {
        this.product = product;
        this.unit = unit;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public double getTotalAmount() {
        return this.getUnit() * this.getProduct().getAmount();
    }
}

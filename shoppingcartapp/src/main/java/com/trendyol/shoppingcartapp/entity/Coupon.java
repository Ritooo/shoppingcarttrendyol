package com.trendyol.shoppingcartapp.entity;

import com.trendyol.shoppingcartapp.core.DiscountType;


public class Coupon {

    public Coupon(double cartAmount, double discount, DiscountType discountType) {
        this.cartAmount = cartAmount;
        this.discount = discount;
        this.discountType = discountType;
    }

    double cartAmount;
    double discount;
    DiscountType discountType;

    public double getCartAmount() {
        return cartAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setCartAmount(double cartAmount) {
        this.cartAmount = cartAmount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }
}

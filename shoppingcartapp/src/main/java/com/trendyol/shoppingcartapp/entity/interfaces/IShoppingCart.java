package com.trendyol.shoppingcartapp.entity.interfaces;

import com.trendyol.shoppingcartapp.entity.Coupon;
import com.trendyol.shoppingcartapp.entity.Product;


public interface IShoppingCart {

    void addItem(Product p, int unit);

    void applyDiscounts(double... campaign);

    void applyCoupon(Coupon coupon);

    double getTotalAmountAfterDiscounts();

    double getCouponDiscounts();

    double getCampaignDiscounts();

    double getDeliveryCost();

    void print();
}

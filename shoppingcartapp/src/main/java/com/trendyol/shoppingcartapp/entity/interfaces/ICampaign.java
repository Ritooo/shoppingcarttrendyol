package com.trendyol.shoppingcartapp.entity.interfaces;

import com.trendyol.shoppingcartapp.entity.ShoppingCart;

public interface ICampaign {
    double calculateCartDiscount(ShoppingCart shoppingCart);
}

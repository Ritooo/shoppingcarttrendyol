package com.trendyol.shoppingcartapp.entity.interfaces;

import com.trendyol.shoppingcartapp.entity.ShoppingCart;

public interface IDeliveryCostCalculator {
    double calculateFor(ShoppingCart cart);
}

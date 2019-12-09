package com.trendyol.shoppingcartapp.entity;

import com.trendyol.shoppingcartapp.core.DiscountType;
import com.trendyol.shoppingcartapp.entity.interfaces.ICampaign;

public class Campaign implements ICampaign {
    Category category;
    double discount;
    int productUnit;
    DiscountType discountType;

    public Campaign(Category category, double discount, int productUnit, DiscountType discountType) {
        this.category = category;
        this.discount = discount;
        this.productUnit = productUnit;
        this.discountType = discountType;
        this.category.getCampaigns().add(this);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(int productUnit) {
        this.productUnit = productUnit;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    /**
     * @param shoppingCart Shopping Cart nesnemizi bu methoda gönderip indirimlerimizi hesaplayıp geriye double tipinde değer dönüyoruz.
     */
    public double calculateCartDiscount(ShoppingCart shoppingCart) {
        double calculatedDiscount = 0;

        int totalProductByCategory = shoppingCart.getTotalProductByCategory(this.getCategory());
        if (totalProductByCategory > this.getProductUnit()) {
            switch (this.getDiscountType()) {
                case Amount:
                    calculatedDiscount = this.getDiscount();
                    break;
                case Rate:
                    double totalAmountByCategory = shoppingCart.getTotalAmountByCategory(this.getCategory());
                    calculatedDiscount = totalAmountByCategory / 100 * this.getDiscount();
                    break;
            }
        }

        return calculatedDiscount;
    }
}

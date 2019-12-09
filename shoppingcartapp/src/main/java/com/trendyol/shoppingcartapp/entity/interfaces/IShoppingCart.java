package com.trendyol.shoppingcartapp.entity.interfaces;

import com.trendyol.shoppingcartapp.entity.Category;
import com.trendyol.shoppingcartapp.entity.Coupon;
import com.trendyol.shoppingcartapp.entity.Product;


public interface IShoppingCart {

    void addItem(Product p, int unit);

    /**
     * @param discounts maliyet hesaplaması
     */
    void applyDiscounts(double... discounts);

    /**
     * @param coupon sisteme girilen kupona göre maliyet hesaplaması
     */
    void applyCoupon(Coupon coupon);

    /**
     * sepetteki toplam tutar
     */
    double getTotalAmount();

    /**
     * sepetteki toplam ürün sayısı
     */
    int getNUmberOfProducts();

    /**
     * @param category kategoriye göre toplam tutar
     */
    double getTotalAmountByCategory(Category category);

    /**
     * @param category kategoriye göre toplam adet
     */
    int getTotalProductByCategory(Category category);

    /**
     * indirimlerden sonraki toplam tutar
     */
    double getTotalAmountAfterDiscounts();

    /**
     * Kupona göre indirim
     */
    double getCouponDiscounts();

    /**
     * Kampanyaya göre indirim
     */
    double getCampaignDiscounts();

    double getDeliveryCost();

    void print();
}

package com.trendyol.shoppingcartapp;

import com.trendyol.shoppingcartapp.core.DiscountType;
import com.trendyol.shoppingcartapp.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ShoppingCartCase {

    private ShoppingCart shoppingCart;
    private Category c1;
    private Product p1;
    private Product p2;

    private Campaign campaign1;
    private Campaign campaign2;
    private Campaign campaign3;

    private Coupon coupon1;

    private DeliveryCostCalculator deliveryCostCalculator;

    @BeforeEach
    public void setup() {
        //Create Category
        c1 = new Category("food");

        //Create Product
        p1 = new Product("Apple", 100.0, c1);
        p2 = new Product("Almond", 150.0, c1);

        campaign1 = new Campaign(c1, 20.0, 3, DiscountType.Rate);
        campaign2 = new Campaign(c1, 50.0, 5, DiscountType.Rate);
        campaign3 = new Campaign(c1, 5.0, 5, DiscountType.Amount);

        coupon1 = new Coupon(100.0, 10, DiscountType.Rate);

        deliveryCostCalculator = new DeliveryCostCalculator(1, 2, 2.99);
        shoppingCart = new ShoppingCart();
    }

    @Test
    @DisplayName("campaign 1 discount")
    void campaignTest1() {

        shoppingCart.addItem(p1, 3);
        shoppingCart.addItem(p2, 1);

        double discount1 = campaign1.calculateCartDiscount(shoppingCart);

        assertEquals(discount1, 90);
    }

    @Test
    @DisplayName("campaign 2 discount")
    void campaignTest2() {

        shoppingCart.addItem(p1, 3);
        shoppingCart.addItem(p2, 1);

        double discount1 = campaign2.calculateCartDiscount(shoppingCart);

        assertEquals(discount1, 0);
    }

    @Test
    @DisplayName("campaign 3 discount")
    void campaignTest3() {

        shoppingCart.addItem(p1, 3);
        shoppingCart.addItem(p2, 1);

        double discount1 = campaign3.calculateCartDiscount(shoppingCart);

        assertEquals(discount1, 0);
    }

    @Test
    @DisplayName("campaign all")
    void campaignTestAll() {

        shoppingCart.addItem(p1, 3);
        shoppingCart.addItem(p2, 1);

        double discount1 = campaign1.calculateCartDiscount(shoppingCart);


        double discount2 = campaign2.calculateCartDiscount(shoppingCart);


        double discount3 = campaign3.calculateCartDiscount(shoppingCart);


        shoppingCart.applyDiscounts(discount1, discount2, discount3);

        double campaignDiscount = shoppingCart.getCampaignDiscount();

        assertEquals(campaignDiscount, 90);

    }

    @Test
    @DisplayName("coupon")
    void couponTest() {

        shoppingCart.addItem(p1, 3);
        shoppingCart.addItem(p2, 1);

        shoppingCart.applyCoupon(coupon1);

        assertEquals(coupon1.getDiscount() > 0, true);

    }

    @Test
    @DisplayName("delivery cost")
    void deliveryCost() {
        shoppingCart.addItem(p1, 3);
        shoppingCart.addItem(p2, 1);

        double discount1 = campaign1.calculateCartDiscount(shoppingCart);


        double discount2 = campaign2.calculateCartDiscount(shoppingCart);


        double discount3 = campaign3.calculateCartDiscount(shoppingCart);


        shoppingCart.applyDiscounts(discount1, discount2, discount3);

        shoppingCart.applyCoupon(coupon1);

        double v = deliveryCostCalculator.calculateFor(shoppingCart);

        assertEquals(v, 12.99);
    }
}

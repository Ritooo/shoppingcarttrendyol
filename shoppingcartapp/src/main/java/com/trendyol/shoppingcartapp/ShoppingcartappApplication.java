package com.trendyol.shoppingcartapp;

import com.trendyol.shoppingcartapp.core.DiscountType;
import com.trendyol.shoppingcartapp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingcartappApplication implements ApplicationRunner {


    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartappApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

           /******** Test Case 1 *******/

        //Create Category
        Category c1 = new Category("food");


        //Create Product
        Product p1 = new Product("Apple", 100.0, c1);
        Product p2 = new Product("Almond", 150.0, c1);


        //Create Shopping Cart
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(p1, 3);
        shoppingCart.addItem(p2, 1);


        //Create Campaign
        Campaign campaign1 = new Campaign(c1, 20.0, 3, DiscountType.Rate);
        Campaign campaign2 = new Campaign(c1, 50.0, 5, DiscountType.Rate);
        Campaign campaign3 = new Campaign(c1, 5.0, 5, DiscountType.Amount);

        //Create Coupon
        Coupon coupon1 = new Coupon(100.0, 10, DiscountType.Rate);

        double discount1 = campaign1.calculateCartDiscount(shoppingCart);
        double discount2 = campaign2.calculateCartDiscount(shoppingCart);
        double discount3 = campaign3.calculateCartDiscount(shoppingCart);
        //Apply Discounts
        shoppingCart.applyDiscounts(discount1, discount2, discount3);
        //Apply discount1
        shoppingCart.applyCoupon(coupon1);

        DeliveryCostCalculator d = new DeliveryCostCalculator(1, 2, 2.99);
        d.calculateFor(shoppingCart);

        shoppingCart.print();


        /******** Test Case 1 *******/


        //Create Category
       /* Category c1 = new Category("Bilgisayar");
        Category c1_1 = new Category("İşlemci", c1);
        Category c1_1_1 = new Category("intel", c1_1);
        Category c1_1_2 = new Category("Amd", c1_1);

        Category c2 = new Category("Moda");
        Category c2_1 = new Category("İç Giyim", c2);


        //Create Product
        Product p_i3 = new Product("i3", 100.0, c1_1_1);
        Product p_i5 = new Product("i5", 200.0, c1_1_1);
        Product p_i7 = new Product("i7", 300.0, c1_1_1);

        Product p_ryzen4 = new Product("ryzen4", 50.0, c1_1_2);
        Product p_ryzen5 = new Product("ryzen5", 75.0, c1_1_2);

        Product p_atlet = new Product("atlet", 5.0, c2_1);


        //Create Shopping Cart
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(p_i3, 2);
        shoppingCart.addItem(p_i3, 3);
        shoppingCart.addItem(p_atlet, 10);


        //Create Campaign
        Campaign campaign1 = new Campaign(c1_1_1, 20.0, 2, DiscountType.Rate);
        Campaign campaign2 = new Campaign(c1_1, 100, 3, DiscountType.Amount);


        //Create Coupon
        Coupon coupon1 = new Coupon(400, 5, DiscountType.Rate);

        double discount1 = campaign1.calculateCartDiscount(shoppingCart);
        double discount2 = campaign2.calculateCartDiscount(shoppingCart);

        //Apply Discounts
        shoppingCart.applyDiscounts(discount1, discount2);
        //Apply discount1
        shoppingCart.applyCoupon(coupon1);

        DeliveryCostCalculator d = new DeliveryCostCalculator(1, 2, 2.99);
        d.calculateFor(shoppingCart);

        shoppingCart.print();
*/
    }
}

package com.trendyol.shoppingcartapp.entity;

import com.trendyol.shoppingcartapp.entity.interfaces.IShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements IShoppingCart {
    List<ShoppingCartItem> shoppingCartItems;
    double campaignDiscount = 0;
    double couponDiscount = 0;
    double deliveryCost = 0;

    public ShoppingCart() {
        this.shoppingCartItems = new ArrayList<>();
    }

    /**
     * @param p    product nesnemizi yollarak Shopping cart item oluşturuyoruz.
     * @param unit girilen adet bilgisi
     */
    @Override
    public void addItem(Product p, int unit) {
        //Aynı üründen iki tane gelirse ürünün adetine ekleme yapıyoruz.
        if (shoppingCartItems.stream().anyMatch(q -> q.getProduct() == p)) {
            ShoppingCartItem shoppingCartItem = this.shoppingCartItems.stream().filter(q -> q.getProduct() == p).findFirst().get();
            shoppingCartItem.setUnit(shoppingCartItem.getUnit() + unit);
        } else {
            ShoppingCartItem item = new ShoppingCartItem(p, unit);
            shoppingCartItems.add(item);
        }
    }

    /**
     * sepetteki toplam tutar
     */
    public double getTotalAmount() {
        double total = 0;
        for (ShoppingCartItem item : this.shoppingCartItems) {
            total += item.getTotalAmount();
        }
        return total;
    }

    /**
     * sepetteki toplam ürün sayısı
     */
    public int getNUmberOfProducts() {
        return this.shoppingCartItems.stream().mapToInt(q -> q.getUnit()).sum();
    }

    /**
     * @param category kategoriye göre toplam tutar
     */
    public double getTotalAmountByCategory(Category category) {
        //girilen kategori üst kategori olabilir alt kategorileri de dolaşarak ilgili tutarlara ulaşıyoruz******
        double total = 0;
        if (category.getChilds() != null) {
            for (Category child : category.getChilds()) {
                total += this.getTotalAmountByCategory(child);
            }
        } else {
            for (ShoppingCartItem item : this.shoppingCartItems) {
                if (item.getProduct().getCategory().equals(category)) {
                    total += item.getUnit() * item.getProduct().getAmount();
                }
            }
        }
        return total;
    }

    /**
     * @param category kategoriye göre toplam adet
     */
    public int getTotalProductByCategory(Category category) {
        //girilen kategori üst kategori olabilir alt kategorileri de dolaşarak ilgili toplama ulaşıyoruz******
        int total = 0;
        if (category.getChilds() != null) {
            for (Category child : category.getChilds()) {
                total += this.getTotalProductByCategory(child);
            }
        } else {
            for (ShoppingCartItem item : this.shoppingCartItems) {
                Category cat = item.getProduct().getCategory();
                if (cat.equals(category)) {
                    total += item.getUnit();
                }
            }
        }
        return total;
    }


    /**
     * @param discounts maliyet hesaplaması
     */
    @Override
    public void applyDiscounts(double... discounts) {
        for (double item : discounts) {
            if (item > campaignDiscount)
                campaignDiscount = item;
        }
    }

    /**
     * @param coupon sisteme girilen kupona göre maliyet hesaplaması
     */
    @Override
    public void applyCoupon(Coupon coupon) {
        double totalAmount = this.getTotalAmount();
        double campaignDiscounts = this.getCampaignDiscounts();
        double subTotal = totalAmount - campaignDiscounts;
        if (subTotal > coupon.getCartAmount()) {
            switch (coupon.getDiscountType()) {
                case Amount:
                    this.couponDiscount = coupon.getDiscount();
                    break;
                case Rate:
                    this.couponDiscount = subTotal * coupon.getDiscount() / 100;
                    break;
            }

        }
    }


    /**
     * indirimlerden sonraki toplam tutar
     */
    @Override
    public double getTotalAmountAfterDiscounts() {
        return this.getTotalAmount() - (this.campaignDiscount + this.couponDiscount);
    }

    /**
     * Kupona göre indirim
     */
    @Override
    public double getCouponDiscounts() {
        return this.couponDiscount;
    }

    /**
     * Kampanyaya göre indirim
     */
    @Override
    public double getCampaignDiscounts() {
        return this.campaignDiscount;
    }


    @Override
    public void print() {

        for (ShoppingCartItem item : this.shoppingCartItems) {

            System.out.println(item.getProduct().getCategory().getTitle() + " - " + item.getProduct().getName() + " - " + item.getUnit() + " - " + item.getProduct().getAmount() + " - " + (item.getUnit() * item.getProduct().getAmount()));

        }

        System.out.println("Total Amount => " + this.getTotalAmount());
        System.out.println("Total After Discount => " + this.getTotalAmountAfterDiscounts());
        System.out.println("Coupon Discount => " + this.getCouponDiscounts());
        System.out.println("Campaign Discount => " + this.getCampaignDiscounts());
        System.out.println("Delivery Cost => " + this.getDeliveryCost());

    }

    public double getCampaignDiscount() {
        return campaignDiscount;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    @Override
    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setCampaignDiscount(double campaignDiscount) {
        this.campaignDiscount = campaignDiscount;
    }

    public void setCouponDiscount(double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }


}

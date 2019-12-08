package com.trendyol.shoppingcartapp.entity;

public class Product {

    String name;
    double amount;
    Category category;

    public Product(String name, double amount, Category category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        category.getProducts().add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

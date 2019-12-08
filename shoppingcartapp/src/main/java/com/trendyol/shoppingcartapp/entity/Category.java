package com.trendyol.shoppingcartapp.entity;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Category {

    public Category(String title, @Nullable Category parentCategory) {
        this.title = title;
        this.parentCategory = parentCategory;
        this.products = new ArrayList<>();

        if (parentCategory != null) {
            parentCategory.addChild(this);
        }
    }

    public Category(String title) {
        this.title = title;
        this.products = new ArrayList<>();
    }

    String title;

    @Nullable
    Category parentCategory;

    List<Category> childs;

    List<Product> products;

    List<Campaign> campaigns = new ArrayList<>();

    public void addChild(Category category) {
        if (this.childs == null)
            this.childs = new ArrayList<>();

        this.childs.add(category);
    }

    public String getTitle() {
        return title;
    }

    @Nullable
    public Category getParentCategory() {
        return parentCategory;
    }

    public List<Category> getChilds() {
        return childs;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setParentCategory(@Nullable Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public void setChilds(List<Category> childs) {
        this.childs = childs;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}

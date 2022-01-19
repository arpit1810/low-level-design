package com.company.entity;

public class Product {
    private String productId;
    private String name;
    private int count;


    public Product(String productId, String name, int count) {
        this.productId = productId;
        this.name = name;
        this.count = count;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}

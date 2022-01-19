package com.company.entity;

import java.time.LocalDateTime;

public class Order {
    private String orderId;
    private String productId;
    private int count;
    private OrderStatus orderStatus;
    private LocalDateTime orderTime;

    public Order(String orderId, String productId, int count) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.orderStatus = OrderStatus.BLOCKED;
        this.orderTime = LocalDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", count=" + count +
                ", orderStatus=" + orderStatus +
                ", orderTime=" + orderTime +
                '}';
    }
}
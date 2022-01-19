package com.company.Manager;

import com.company.entity.Order;
import com.company.entity.OrderStatus;
import com.company.entity.Product;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class ProductManager {
    private ConcurrentHashMap<String, Product> productMap;
    private ConcurrentHashMap<String, Order> orderMap;
    private ConcurrentSkipListSet<String> confirmedOrders;
    private ConcurrentSkipListSet<String> blockedOrders;
    private ConcurrentHashMap<String, LinkedHashSet<String>> blockedInventory;

    public ProductManager() {
        productMap = new ConcurrentHashMap<>();
        orderMap = new ConcurrentHashMap<>();
        confirmedOrders = new ConcurrentSkipListSet<>();
        blockedOrders = new ConcurrentSkipListSet<>();
        blockedInventory = new ConcurrentHashMap<>();
        // Start seperate thread in product manager which would unblock the inventory
        Thread removeExpiredBlockageThread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while(1>0)
                        {
                            LocalDateTime localDateTime = LocalDateTime.now().minusSeconds(10);
                            for(String orderId : blockedOrders)
                            {
                                Iterator<String> iterator =
                                        blockedInventory.get(orderMap.get(orderId).getProductId()).iterator();
                                while(iterator.hasNext())
                                {
                                    String productOrder = iterator.next();

                                    synchronized (orderMap.get(productOrder)) {
                                        if (orderMap.get(productOrder).getOrderTime().isAfter(localDateTime))
                                            break;

                                        orderMap.get(productOrder).setOrderStatus(OrderStatus.EXPIRED);
                                        blockedOrders.remove(productOrder);
                                        blockedInventory.get(orderMap.get(orderId).getProductId()).remove(productOrder);
                                        productMap.get(orderMap.get(orderId).getProductId()).setCount(
                                                productMap.get(orderMap.get(orderId).getProductId()).getCount() +
                                                        orderMap.get(productOrder).getCount()
                                        );
                                        System.out.println("Adding back to inventory");
                                    }
                                }
                            }

                            try {
                                Thread.sleep(500);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        removeExpiredBlockageThread.start();
    }

    public void createProduct(String productId, String name, int count)
    {
        if(productMap.get(productId) != null)
        {
            System.out.println("Product with id already exists so not addding");
            return;
        }

        productMap.put(productId, new Product(productId, name, count));
    }

    public int getInventory(String productId)
    {
        if(productMap.get(productId) == null)
        {
            System.out.println("Inventory for product is not there");
            return 0;
        }

        return productMap.get(productId).getCount();
    }

    public boolean blockInventory(String productId, int count, String orderId)
    {
        if(productMap.get(productId) == null)
            return false;

        Product product = productMap.get(productId);
        boolean result = true;

        synchronized (product) {
            // If sufficient count is there in productId
            if(product.getCount() < count) {
                System.out.println("Product inventory count is less than count asked, order not created");
                result = false;
            }

            else {
                // Create order
                System.out.println("Inventory looks fine, Adding order");
                Order order = new Order(orderId, productId, count);
                orderMap.put(orderId, order);
                product.setCount(
                        product.getCount() - count
                );
                // Add the order in block order
                blockedOrders.add(orderId);
                // Add the order in blockedInventory
                if(blockedInventory.get(productId) == null)
                    blockedInventory.put(productId, new LinkedHashSet<>());
                blockedInventory.get(productId).add(orderId);
                System.out.println("Order added successfully");
            }
        }

        return result;
    }

    public void confirmOrder(String orderId)
    {
        if(orderMap.get(orderId) == null)
        {
            System.out.println("order do not exist with this id");
            return;
        }

        if(orderMap.get(orderId).getOrderStatus() == OrderStatus.EXPIRED)
        {
            System.out.println("Order time has expired so not confirming the order");
            return;
        }

        synchronized(orderMap.get(orderId)) {
            orderMap.get(orderId).setOrderStatus(OrderStatus.CONFIRMED);
            blockedOrders.remove(orderId);
            confirmedOrders.add(orderId);
            blockedInventory.get(orderMap.get(orderId).getProductId()).remove(orderId);
            System.out.println("Order is confirmed");
        }
    }


    public ConcurrentHashMap<String, Product> getProductMap() {
        return productMap;
    }

    public void setProductMap(ConcurrentHashMap<String, Product> productMap) {
        this.productMap = productMap;
    }

    @Override
    public String toString() {
        return "ProductManager{" +
                "productMap=" + productMap +
                ", orderMap=" + orderMap +
                ", confirmedOrders=" + confirmedOrders +
                ", blockedOrders=" + blockedOrders +
                ", blockedInventory=" + blockedInventory +
                '}';
    }
}

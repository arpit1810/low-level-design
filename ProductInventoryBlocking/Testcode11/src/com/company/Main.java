package com.company;

import com.company.Manager.ProductManager;

public class Main {

    public static void main(String[] args) throws InterruptedException{
	// write your code

        ProductManager productManager = new ProductManager();
        productManager.createProduct("11","aa", 10);
        productManager.createProduct("22","bb", 20);
        System.out.println(productManager);

        System.out.println("Product id 11 inventory is : " + productManager.getInventory("11"));

        System.out.println("Product id 33 inventory is : " + productManager.getInventory("33"));

        productManager.blockInventory("11", 100, "101");

        productManager.blockInventory("11", 5, "102");
        System.out.println(productManager);

        productManager.confirmOrder("101");
        productManager.confirmOrder("102");
        System.out.println(productManager);


        productManager.blockInventory("11", 2, "103");
        productManager.blockInventory("22", 2, "104");
        System.out.println(productManager);
        Thread.sleep(12000);
        System.out.println(productManager);


    }
}

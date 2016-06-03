package com.tsmith.mypackage;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
	    StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.add(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.add(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.add(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.add(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.add(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.add(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.add(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.add(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.add(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.add(temp);

        System.out.println(stockList);

        Basket myBasket = new Basket("myBasket");
        Basket yourBasket = new Basket("yourBasket");

        sellItem(myBasket, "bread", 50);
        System.out.println(myBasket);
        System.out.println(stockList);
        removeItem(myBasket, "bread", 10);
        System.out.println(myBasket);
        System.out.println(stockList);
        checkOut(myBasket);

        System.out.println(stockList);

    }

    public static int sellItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item + ".");
            return 0;
        }
        if(stockList.reserveStock(stockItem, quantity) != 0) {
            basket.addToBasket(stockItem, quantity);
            stockItem.adjustReserved(quantity);
            return quantity;
        }
        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity) {
        StockItem itemToRemove = stockList.get(item);
        if(basket.getBasketItems().containsKey(itemToRemove)==false) {
            System.out.println("Item not in basket.");
            return 0;
        }
        int currentReserved = basket.getBasketItems().get(itemToRemove);
        if(currentReserved < quantity) {
            System.out.println("There aren't that many " + item + " in the basket.");
            return 0;
        }
        itemToRemove.adjustReserved(-quantity);
        return basket.getBasketItems().put(itemToRemove, (currentReserved - quantity));
    }

    public static void checkOut(Basket basket) {
        for(Map.Entry<StockItem, Integer> item: basket.getBasketItems().entrySet()) {
            item.getKey().adjustStock(-item.getValue());
        }
        basket.getBasketItems().clear();
        System.out.println("Thank you for shopping with us. Please come again.");
    }
}

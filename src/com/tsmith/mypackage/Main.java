package com.tsmith.mypackage;

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

        System.out.println("sellItem() bread 50 called...");
        sellItem(myBasket, "bread", 50);
        System.out.println(myBasket);
        System.out.println(stockList);
        System.out.println("basket.removeItem() bread 10 called...");
        myBasket.removeItem("bread", 10);
        System.out.println("sellItem() bread 20 called...");
        sellItem(yourBasket,"bread",20);

        System.out.println(myBasket);
        System.out.println(yourBasket);
        System.out.println(stockList);
        System.out.println("myBasket checkout called...");
        myBasket.checkOut();

        System.out.println(stockList);
        System.out.println("yourBasket removeItem() bread 50 called...");
        yourBasket.removeItem("bread", 50);
        System.out.println("yourBasket removeItem() car 1 called...");
        yourBasket.removeItem("car", 1);
        System.out.println("yourBasket checkout() called...");
        yourBasket.checkOut();
        System.out.println(stockList);

    }

    public static int sellItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item + ".");
            return 0;
        }
        if(stockList.reserveStock(stockItem, quantity) != 0) {
            basket.addItem(stockItem, quantity);
            stockItem.adjustReserved(quantity);
            return quantity;
        }
        return 0;
    }

}

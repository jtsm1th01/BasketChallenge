package com.tsmith.mypackage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dev on 17/02/2016.
 */
public class Basket {
    private final String name;
    private final Map<StockItem, Integer> basketItems;

    public Basket(String name) {
        this.name = name;
        this.basketItems = new HashMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0) && (quantity <= (item.availableStock()))) {
            int inBasket = basketItems.getOrDefault(item, 0);
            basketItems.put(item, inBasket + quantity);
            item.adjustReserved(quantity);
            return inBasket;
        }
        return 0;
    }

    public Map<StockItem, Integer> getBasketItems() {
        return this.basketItems;
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + basketItems.size() + " items\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : basketItems.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " purchased\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }
}

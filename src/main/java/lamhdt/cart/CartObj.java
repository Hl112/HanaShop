/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamhdt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HL
 */
public class CartObj implements Serializable {

    private Map<Integer, Integer> items = null;

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void addItemToCart(int id) {
        if (items == null) {
            items = new HashMap<>();
        }
        int quantity = 1;
        if (items.containsKey(id)) {
            quantity = items.get(id) + 1;
        }
        items.put(id, quantity);
    }

    public void removeItemFromCart(int id) {
        if (items == null) {
            return;
        }
        if (items.containsKey(id)) {
            items.remove(id);
            if (items.isEmpty()) {
                items = null;
            }
        }
    }

    public void updateQuantity(int id, int quantity) {
        if (items == null) {
            return;
        }
        if(quantity == 0) return ;
        if (items.containsKey(id)) {
            items.put(id, quantity);
        }
    }
}
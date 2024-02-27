/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.Cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Goby
 */
public class CartObject implements Serializable {

    private Map<String, Integer> items;

    //không có set vì phải bỏ từng món hàng vào giỏ
    public Map<String, Integer> getItems() {
        return items;
    }

    public boolean addItemToCart(String id) {
        boolean result = false;
        //1. Check valid id
        // id hợp lệ: không phải null, không chứa khoảng trắng
        if (id == null || id.trim().isEmpty()) {
            return result;
        }
        //2. Check existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. Check existed item
        int quantity = 1;
        if (this.items.containsKey(id)) {
            quantity = this.items.get(id) + 1;
        }
        //4. drop item to items
        this.items.put(id, quantity);
        result = true;
        return result;
    }

    public boolean removeItemFromCart(String id) {
        boolean result = false;
        //1. kiểm tra ngăn chứa đồ có tồn tại hay không?
        if (this.items != null) {
            //2. kiểm tra món đồ có tồn tại hay không
            if (this.items.containsKey(id)) {
                //3. bỏ đồ ra khỏi giỏ
                this.items.remove(id);
                //kiểm tra xem còn phần tử nào trong collection không
                //nếu không còn thì kill collection
                //lần sau chỉ cần check collection != null là sẽ biết được
                //collection có ít nhất 1 phần tử
                if (this.items.isEmpty()) {
                    this.items = null;
                }
                result = true;
            }
        }
        return result;
    }

    //1. kiểm tra xem id có tồn tại, status, quantity
}

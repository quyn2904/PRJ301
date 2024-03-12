/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.orderDetail;

/**
 *
 * @author Goby
 */
public class OrderDetailDTO {
    private int id;
    private String productId;
    private float unitprice;
    private int quantity;
    private float total;
    private String orderId;

    public OrderDetailDTO() {
    }

    public int getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public float getUnitprice() {
        return unitprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotal() {
        return total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    
}

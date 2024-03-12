/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.product;

import java.io.Serializable;

/**
 *
 * @author Goby
 */
public class ProductDTO implements Serializable{
    private String id;
    private String name;
    private String description;
    private float unitprice;
    private int quantity;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(String id, String name, String description, float unitprice, int quantity, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitprice = unitprice;
        this.quantity = quantity;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getUnitprice() {
        return unitprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}

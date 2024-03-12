/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyenpq.order;

import java.sql.Date;

/**
 *
 * @author Goby
 */
public class OrderDTO {
    private String id;
    private Date date;
    private String name;
    private float total;

    public OrderDTO() {
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public float getTotal() {
        return total;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(float total) {
        this.total = total;
    }    
}

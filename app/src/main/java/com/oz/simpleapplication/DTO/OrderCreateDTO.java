package com.oz.simpleapplication.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderCreateDTO {
    public String name;
    public String phone;
    public String comment;
    public int number;
    public Date date;
    public List<OrderProductCreateDTO> orderProducts;

    public OrderCreateDTO(String name, String phone, List<String> productIds) {
        this.name = name;
        this.phone = phone;
        this.comment = "";
        this.number = (int) (Math.random() * 1_000_000); // случайный номер заказа
        this.date = new Date();
        this.orderProducts = new ArrayList<>();
        for (String productId : productIds) {
            this.orderProducts.add(new OrderProductCreateDTO(productId));
        }
    }
}
package com.oz.simpleapplication.DTO;

import android.text.Editable;

import java.util.List;

public class OrderDTO {
    public String id;
    public String name;
    public String phone;
    public String comment;
    public Integer number;
    public String date;
    public List<OrderProductDTO> orderProducts;
}

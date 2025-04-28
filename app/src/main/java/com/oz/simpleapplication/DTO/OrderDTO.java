package com.oz.simpleapplication.DTO;

import android.text.Editable;

import java.util.List;

public class OrderDTO {
    int id;
    List<String> contents;
    String name;
    String phone;

    public OrderDTO(int id, List<String> contents, Editable name, Editable phone) {
        this.id = id;
        this.contents = contents;
        this.name = String.valueOf(name);
        this.phone = String.valueOf(phone);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

package com.oz.simpleapplication.model;

public class Content {

    int id;
    int category;
    String title;
    String img;
    String description;
    String property;
    String color;
    String text;

    public Content(int id, String title, String img, String description,String property, String color,String text,int category) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.description = description;
        this.property = property;
        this.color = color;
        this.text = text;
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}

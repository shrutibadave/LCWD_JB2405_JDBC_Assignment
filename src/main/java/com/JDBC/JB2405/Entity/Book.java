package com.JDBC.JB2405.Entity;

public class Book {
    int id;
    String name;
    String description;
    int price;
    int available;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public int isAvailable() {
        return available;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}

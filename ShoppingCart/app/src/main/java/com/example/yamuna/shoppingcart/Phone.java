package com.example.yamuna.shoppingcart;

public class Phone {
    private int id;
    private String model;
    private String manufacturer;
    private Integer price;
    private int quantity;
    private String image;


    public Phone(String model, String manufacturer, Integer price, String image) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
        this.image = image;
    }


    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}

package com.example.yamuna.shoppingcart;

public class Buy {

    String username;
    Integer quantity;
    String model;
    String invoiceNumber;

    public Buy(String username, Integer quantity, String model, String invoiceNumber) {
        super();
        this.username = username;
        this.quantity = quantity;
        this.model = model;
        this.invoiceNumber = invoiceNumber;
    }

    public String getUsername() {
        return username;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getModel() {
        return model;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

}


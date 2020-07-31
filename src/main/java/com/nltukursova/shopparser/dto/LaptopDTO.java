package com.nltukursova.shopparser.dto;

import lombok.Data;

@Data
public class LaptopDTO {
    private String name;
    private String url;
    private Double price;
    private final String currency = "UAH";
    private String shopUrl;
    private String shopImage;

    public LaptopDTO buildDto(String name, String url, String price, String shopUrl, String shopImage) {
        this.name = name;
        this.price = Double.valueOf(price);
        this.url = shopUrl + url;
        this.shopUrl = shopUrl;
        this.shopImage = shopImage;
        return this;
    }
}

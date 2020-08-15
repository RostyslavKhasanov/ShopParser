package com.nltukursova.shopparser.domain;

import lombok.Data;

@Data
public class LaptopDTO implements Comparable<LaptopDTO> {
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

    @Override
    public int compareTo(LaptopDTO o) {
        if (price > o.getPrice()) {
            return 1;
        } else if (price.equals(o.getPrice())) {
            return 0;
        } else {
            return -1;
        }
    }
}

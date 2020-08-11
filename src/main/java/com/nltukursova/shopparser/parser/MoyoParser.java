package com.nltukursova.shopparser.parser;

import com.nltukursova.shopparser.domain.LaptopDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MoyoParser implements Parser {

    private static final String SHOP_URL = "https://www.moyo.ua";
    private static final Logger log = LogManager.getLogger(MoyoParser.class.getName());

    @Override
    public Document getPage(String laptopName) {
        Document doc = null;
        String query = SHOP_URL + "/search/new?sorting=4&id=1&title=1&code=1&barcode=1&available=0&partial=1&category=2805&q=" + laptopName;
        try {
            doc = Jsoup.connect(query).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    @Override
    public LaptopDTO getLaptop(String name) {
        Document htmlPage = getPage(name);
        LaptopDTO laptopDTO = parsePageByFields(htmlPage);
        return laptopDTO;
    }

    @Override
    public LaptopDTO parsePageByFields(Document doc) {
        try {
            //get list of founded items
            Elements itemList = doc.getElementsByClass("product-tile_container");

            //get name of first item in the search
            String name = itemList.first().select("div.product-tile_title").first()
                    .select("a").text();
            log.info("Moyo name: " + name);

            //get price
            String price = itemList.first().select("div.product-tile_price-current").first()
                    .select("span.product-tile_price-value").text().replaceAll("\\s+", "");
            log.info("Moyo price " + price);

            //get url to the product in the shop
            String laptopUrl = SHOP_URL + itemList.first().select("div.product-tile_title").first()
                    .select("a").attr("href");
            log.info("Moyo laptopUrl " + laptopUrl);

            //need to realize
            String shopImage = "https://www.moyo.ua/images/moyo_logo.png";

            return new LaptopDTO().buildDto(name, laptopUrl, price, SHOP_URL, shopImage);
        } catch (NullPointerException npe) {
            log.info("Moyo returned null for product");
            return null;
        }
    }
}

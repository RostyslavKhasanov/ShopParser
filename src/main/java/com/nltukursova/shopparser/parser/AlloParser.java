package com.nltukursova.shopparser.parser;

import com.nltukursova.shopparser.domain.LaptopDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AlloParser implements Parser {

    private static final String SHOP_URL = "https://allo.ua";
    private static final Logger log = LogManager.getLogger(AlloParser.class.getName());

    @Override
    public Document getPage(String laptopName) {
        Document doc = null;
        String query = SHOP_URL + "/ua/catalogsearch/result/?q=" + laptopName;
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
            Elements itemList = doc.getElementsByClass("category-products");

            //get first item
            String name = itemList.first().select("div.product-name-container").first()
                    .select("a").select("span").text();
            log.info("Allo name: " + name);

            //get price
            String priceOld = itemList.first().selectFirst("div.price-box").select("span.sum").text()
                    .replaceAll("\\s+", "");
            String priceNew = itemList.first().selectFirst("div.price-box").select("span.new_sum").text()
                    .replaceAll("\\s+", "");

            String price;
            if (priceOld.isEmpty()) {
                price = priceNew;
            } else {
                price = priceOld;
            }
            log.info("Allo price " + price);

            //get url to the product in the shop
            String laptopUrl = itemList.first().select("div.product-name-container").first()
                    .select("a").attr("href");
            laptopUrl = laptopUrl.substring(9);
            log.info("Allo laptopUrl " + laptopUrl);

            //need to realize
            String shopImage = "";

            return new LaptopDTO().buildDto(name, laptopUrl, price, SHOP_URL, shopImage);
        } catch (NullPointerException npe) {
            log.info("Allo returned null for product");
            return null;
        }
    }

    public static void main(String[] args) {
        AlloParser alloParser = new AlloParser();
        System.out.println(alloParser.getLaptop("mac"));
    }
}

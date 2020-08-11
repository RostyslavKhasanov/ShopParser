package com.nltukursova.shopparser.parser;

import com.nltukursova.shopparser.domain.LaptopDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BrainParser implements Parser {

    private static final String SHOP_URL = "https://brain.com.ua";
    private static final Logger log = LogManager.getLogger(BrainParser.class.getName());

    @Override
    public Document getPage(String laptopName) {
        Document doc = null;
        String query = SHOP_URL + "/search/category/Noutbuky-c1191/?Search=" + laptopName;
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
            Elements itemList = doc.getElementsByClass("tab-content");

            //get name of first item in the search
            String name = itemList.first().select("div.description-wrapper").first()
                    .select("a").text();
            log.info("Brain name: " + name);

            //get price
            String price = itemList.first().select("div.br-pp-ss").first()
                    .selectFirst("span").text().replaceAll("\\s+", "");
            log.info("Brain price " + price);

            //get url to the product in the shop
            String laptopUrl = SHOP_URL + itemList.first().select("div.description-wrapper").first()
                    .select("a").attr("href");
            log.info("Brain laptopUrl " + laptopUrl);

            //need to realize
            String shopImage = "https://brain.com.ua/Noutbuk_Apple_MacBook_Air_A2179_MWTJ2RU_A-p681119.html";

            return new LaptopDTO().buildDto(name, laptopUrl, price, SHOP_URL, shopImage);
        } catch (NullPointerException npe) {
            log.info("Brain returned null for product");
            return null;
        }
    }
}

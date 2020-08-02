package com.nltukursova.shopparser.parser;

import com.nltukursova.shopparser.domain.LaptopDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CitrusParser implements Parser {

    private static final String SHOP_URL = "https://www.citrus.ua";
    private static final Logger log = LogManager.getLogger(CitrusParser.class.getName());

    @Override
    public LaptopDTO getLaptop(String laptopName) {
        Document htmlPage = getPage(laptopName);
        LaptopDTO laptopDTO = parsePageByFields(htmlPage);
        return laptopDTO;
    }

    @Override
    public LaptopDTO parsePageByFields(Document doc) {

        try {
            //get list of founded items
            Elements itemList = doc.getElementsByClass("product-card product-card--mini");

            //get first item
            Element foundedLaptop = itemList.first();

            //get parameters from page to build laptop dto
            //get name
            String name = foundedLaptop.select("div.title-itm").select("h5").text();
            log.info("Citrus name: " + name);

            //get price
            String price = foundedLaptop.selectFirst("div.base-price").select("span").text().replaceAll("\\s+", "");
            log.info("Citrus price " + price);

            //get url to the product in the shop
            String laptopUrl = foundedLaptop.selectFirst("a.card-product-link").attr("href");
            log.info("Citrus laptopUrl " + laptopUrl);

            //need to realize
            String shopImage = "";

            return new LaptopDTO().buildDto(name, laptopUrl, price, SHOP_URL, shopImage);
        } catch (NullPointerException npe) {
            log.info("Citrus returned null for product");
            return null;
        }
    }

    @Override
    public Document getPage(String laptopName) {
        Document doc = null;
        String query = "https://www.citrus.ua/search?query=" + laptopName + "&categories=96";
        try {
            doc = Jsoup.connect(query).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}

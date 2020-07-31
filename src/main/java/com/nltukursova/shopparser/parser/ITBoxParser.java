package com.nltukursova.shopparser.parser;

import com.nltukursova.shopparser.dto.LaptopDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

@Log4j2
public class ITBoxParser implements Parser {

    private static final String SHOP_URL = "https://www.itbox.ua";
    private static final Logger log = LogManager.getLogger(ITBoxParser.class);

    @Override
    public Document readPage(String laptopName) {
        Document doc = null;
        String query = "https://www.itbox.ua/search/category/Noutbuki-c4093/?Search=" + laptopName;
        try {
            doc = Jsoup.connect(query).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    @Override
    public LaptopDTO getLaptop(String name) {
        Document htmlPage = readPage(name);
        LaptopDTO laptopDTO = parsePageByFields(htmlPage);
        return laptopDTO;
    }

    @Override
    public LaptopDTO parsePageByFields(Document doc) {
        try {
            //get list of founded items
            Elements itemList = doc.getElementsByClass("products");

            //get first item
            String name = itemList.first().select("div.stuff-caption").select("a").first().text();
            log.info("ITBox name: " + name);

            //get price
            String price = itemList.first().selectFirst("div.stuff-price__row")
                    .select("span").select("strong").text().replaceAll("\\s+", "");
            log.info("ITBox price " + price);

            //get url to the product in the shop
            String laptopUrl = itemList.first().select("div.stuff-caption").select("a").first()
                    .attr("href");
            log.info("ITBox laptopUrl " + laptopUrl);

            //need to realize
            String shopImage = "";

            return new LaptopDTO().buildDto(name, laptopUrl, price, SHOP_URL, shopImage);
        } catch (NullPointerException npe) {
            log.info("ITBox returned null for product");
            return null;
        }
    }

    public static void main(String[] args) {
        ITBoxParser rozetkaParser = new ITBoxParser();
        System.out.println(rozetkaParser.getLaptop("dell"));
    }
}

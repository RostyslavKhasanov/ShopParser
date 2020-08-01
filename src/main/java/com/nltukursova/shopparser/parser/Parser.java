package com.nltukursova.shopparser.parser;

import com.nltukursova.shopparser.domain.LaptopDTO;
import org.jsoup.nodes.Document;

public interface Parser {
    Document getPage(String laptopName);

    LaptopDTO getLaptop(String name);

    LaptopDTO parsePageByFields(Document document);
}

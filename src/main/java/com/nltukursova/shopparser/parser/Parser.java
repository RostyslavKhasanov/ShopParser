package com.nltukursova.shopparser.parser;

import com.nltukursova.shopparser.dto.LaptopDTO;
import org.jsoup.nodes.Document;

public interface Parser {
    Document readPage(String laptopName);

    LaptopDTO getLaptop(String name);

    LaptopDTO parsePageByFields(Document document);
}
package com.nltukursova.shopparser.parser;

import com.nltukursova.shopparser.domain.LaptopDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParserService {

    private static final List<Parser> PARSERS = new ArrayList<Parser>() {{
        add(new AlloParser());
        add(new CitrusParser());
        add(new ITBoxParser());
    }};

    public List<LaptopDTO> getLaptopByShops(String name) {
        List<LaptopDTO> laptopDTOS = new ArrayList<>();
        for (Parser parser : PARSERS) {
            laptopDTOS.add(parser.getLaptop(name));
        }
        return laptopDTOS;
    }
}

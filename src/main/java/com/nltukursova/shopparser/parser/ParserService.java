package com.nltukursova.shopparser.parser;

import com.nltukursova.shopparser.domain.LaptopDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParserService {

    private static final List<Parser> PARSERS = Arrays.asList(
            new CitrusParser(),
            new AlloParser(),
            new BrainParser(),
            new ITBoxParser(),
            new MoyoParser());

    public List<LaptopDTO> getLaptopByShops(String name) {
        List<LaptopDTO> laptopDTOS = new ArrayList<>();
        for (Parser parser : PARSERS) {
            LaptopDTO laptopDTO = parser.getLaptop(name);
            if (laptopDTO != null) {
                laptopDTOS.add(laptopDTO);
            }
        }
        laptopDTOS = laptopDTOS.stream().filter(x -> x.getName().contains(name)).collect(Collectors.toList());
        return laptopDTOS;
    }
}

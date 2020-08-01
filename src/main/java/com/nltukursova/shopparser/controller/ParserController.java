package com.nltukursova.shopparser.controller;

import com.nltukursova.shopparser.domain.LaptopDTO;
import com.nltukursova.shopparser.parser.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parser")
public class ParserController {

    @Autowired
    ParserService parserService;

    @GetMapping
    public ResponseEntity<?> getLaptops(@RequestParam String name) {
        return new ResponseEntity<List<LaptopDTO>>(parserService.getLaptopByShops(name), HttpStatus.OK);
    }
}

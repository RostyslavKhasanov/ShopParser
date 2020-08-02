package com.nltukursova.shopparser.controller;

import com.nltukursova.shopparser.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("laptop")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @GetMapping
    public ResponseEntity<List<String>> getLaptopsName(@RequestParam String name) {
        return new ResponseEntity<>(laptopService.getLaptopsName(name), HttpStatus.OK);
    }
}

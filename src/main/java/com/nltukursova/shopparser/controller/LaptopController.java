package com.nltukursova.shopparser.controller;

import com.nltukursova.shopparser.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

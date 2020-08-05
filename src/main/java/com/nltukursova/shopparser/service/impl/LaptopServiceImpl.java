package com.nltukursova.shopparser.service.impl;

import com.nltukursova.shopparser.entity.LaptopEntity;
import com.nltukursova.shopparser.repository.LaptopRepository;
import com.nltukursova.shopparser.service.LaptopService;
import com.nltukursova.shopparser.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public List<String> getLaptopsName(String name) {
        List<LaptopEntity> laptopEntities = laptopRepository.findAllByNameLike("%" + name + "%");
        List<String> names = new ArrayList<String>() {{
            for (LaptopEntity laptop : laptopEntities) {
                add(laptop.getName());
            }
        }};
        return names;
    }
}

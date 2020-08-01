package com.nltukursova.shopparser.repository;

import com.nltukursova.shopparser.entity.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, Long> {

    LaptopEntity getAllByNameLike(String name);
}

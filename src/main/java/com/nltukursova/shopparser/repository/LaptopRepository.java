package com.nltukursova.shopparser.repository;

import com.nltukursova.shopparser.entity.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, Long> {

    @Query(value = "select * from laptops where LOWER(name) like LOWER(:name)", nativeQuery = true)
    List<LaptopEntity> findAllByNameLike(String name);
}

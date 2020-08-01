package com.nltukursova.shopparser.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "laptops")
@Data
public class LaptopEntity extends BaseEntity {
    private String name;
}

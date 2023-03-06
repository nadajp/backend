package com.nadajp.sample.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resource {
    Integer id;
    String name;
    Integer year;
    String color;
    String pantone_value;

    public Resource() {
    }

    public Resource(Integer id, String name, Integer year, String color, String pantone_value) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantone_value = pantone_value;
    }

}

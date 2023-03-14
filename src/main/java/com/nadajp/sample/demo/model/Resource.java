package com.nadajp.sample.demo.model;

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
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void setPantone_value(String pantoneValue) {
        this.pantone_value = pantoneValue;
    }

    public String getPantone_value() {
        return this.pantone_value;
    }

}

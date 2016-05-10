package com.homework.entities;

public class Card {
    private Integer id;
    private String name;
    private Integer id_client;
    private Integer id_bill;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getId_client() {
        return id_client;
    }
    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }
    public Integer getId_bill() {
        return id_bill;
    }
    public void setId_bill(Integer id_bill) {
        this.id_bill = id_bill;
    }
}

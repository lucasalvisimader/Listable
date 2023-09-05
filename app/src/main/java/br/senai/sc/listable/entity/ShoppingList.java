package br.senai.sc.listable.entity;

import java.util.List;
import java.util.TimeZone;

public class ShoppingList {
    private String id;
    private String name;
    private TimeZone dateTime;
    private Integer total;
    private Integer done;
    private List<Item> items;
    private String idUser;

    public ShoppingList(String id, String name, String idUser) {
        this.id = id;
        this.name = name;
        this.idUser = idUser;
    }

    public ShoppingList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeZone getDateTime() {
        return dateTime;
    }

    public void setDateTime(TimeZone dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}

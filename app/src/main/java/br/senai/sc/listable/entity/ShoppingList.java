package br.senai.sc.listable.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ShoppingList implements Serializable {
    private String id;
    private String name;
    private Date date;
    private Integer total;
    private boolean done;
    private List<Item> items = new ArrayList<>();
    private Integer itemsDone;

    public ShoppingList(String id, String name) {
        this.id = id;
        this.name = name;
        this.total = 0;
        this.itemsDone = 0;
    }

    public ShoppingList() {
        this.total = 0;
        this.itemsDone = 0;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Integer getItemsDone() {
        return itemsDone;
    }

    public void setItemsDone(Integer itemsDone) {
        this.itemsDone = itemsDone;
    }
}

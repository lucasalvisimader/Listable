package br.senai.sc.listable.entity;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.listable.utils.GeneratorUUID;

public class Item implements Serializable {
    private String id;
    private String name;
    private String category;
    private Integer qtd;
    private String un;
    private String price;
    private String description;
    private boolean finished;
    private final List<ShoppingList> shoppingLists = new ArrayList<>();

    public Item() {}

    public Item(String name) {
        this.id = GeneratorUUID.generate();
        this.name = name;
        this.category = "default";
        this.finished = true;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(ShoppingList shoppingList) {
        Log.i("josivaldo", String.valueOf(shoppingLists.size()));
        this.shoppingLists.add(shoppingList);
    }
}

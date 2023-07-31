package br.senai.sc.listable.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

import br.senai.sc.listable.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView name = itemView.findViewById(R.id.item_name);
    private int category;
    private Integer qtd;
    private String un;
    private String price;
    private String description;
    private Date date;
    private boolean finished;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public ItemViewHolder(@NonNull View itemView, TextView name, int category, Integer qtd, String un, String price, String description, Date date, boolean finished) {
        super(itemView);
        this.name = name;
        this.category = category;
        this.qtd = qtd;
        this.un = un;
        this.price = price;
        this.description = description;
        this.date = date;
        this.finished = finished;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}

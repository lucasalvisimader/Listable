package br.senai.sc.listable.viewHolder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

import br.senai.sc.listable.R;

public class ShoppingListViewHolder extends RecyclerView.ViewHolder {

    private TextView name = itemView.findViewById(R.id.shopping_list_name);
    private Date date;
    private int category;
    private Integer qtd;
    private String un;
    private String price;
    private String description;
    private boolean done;
    private TextView totalAndItemsDone = itemView.findViewById(R.id.shopping_list_total_and_done);
    private ProgressBar progressBar = itemView.findViewById(R.id.shopping_list_progress_bar);

    public ShoppingListViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public ShoppingListViewHolder(@NonNull View itemView, TextView name, Date date, int category, Integer qtd, String un, String price, String description, boolean done, TextView totalAndItemsDone, ProgressBar progressBar) {
        super(itemView);
        this.name = name;
        this.date = date;
        this.category = category;
        this.qtd = qtd;
        this.un = un;
        this.price = price;
        this.description = description;
        this.done = done;
        this.totalAndItemsDone = totalAndItemsDone;
        this.progressBar = progressBar;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public TextView getTotalAndItemsDone() {
        return totalAndItemsDone;
    }

    public void setTotalAndItemsDone(TextView totalAndItemsDone) {
        this.totalAndItemsDone = totalAndItemsDone;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}

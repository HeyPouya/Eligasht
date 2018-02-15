package ir.eligasht.eligashttest.POJOs;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by pouya on 2/15/18.
 */

public class GoodListDatabaseModel extends RealmObject {
    @PrimaryKey
    int id;
    String name;
    long price;
    int quantity;

    public int getBuyQuantity() {
        return BuyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        BuyQuantity = buyQuantity;
    }

    int BuyQuantity;
    int picAddress;
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(int picAddress) {
        this.picAddress = picAddress;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    int category;
}

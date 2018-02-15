package ir.eligasht.eligashttest.POJOs;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by pouya on 2/15/18.
 */

public class UserBasketDatabaseModel extends RealmObject {
    @PrimaryKey
    int id;
    int productId;
    int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

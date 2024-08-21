package e_commerce.domain.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cart {
    private CartId id;
    private ArrayList<Product> productList;
    private LocalDateTime createdTime;

    public Cart(CartId id, ArrayList<Product> productList) {
        this.id = id;
        this.productList = productList;
    }

    public Cart(ArrayList<Product> productList) {
        this.id = new CartId();
        this.productList = productList;
    }

    public CartId getId() {
        return id;
    }

    public void setId(CartId id) {
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return productList;
    }

    public void setProducts(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}

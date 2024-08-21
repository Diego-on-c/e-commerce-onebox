package e_commerce.domain.contracts;

import e_commerce.domain.models.Cart;
import e_commerce.domain.models.CartId;
import e_commerce.domain.models.Product;

import java.util.ArrayList;
import java.util.UUID;

public interface CartRepository {

    String addCart(Cart cart);

    Cart getCartById(UUID cartId);

    boolean findById(CartId cartId);

    void addProducts(Cart cart);

    void deleteCartById(CartId cartId);

    void truncate();

    int getCartsQuantity();
}

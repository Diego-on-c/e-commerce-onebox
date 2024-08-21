package e_commerce.services;

import e_commerce.domain.contracts.CartRepository;
import e_commerce.domain.exceptions.CartNotFoundException;
import e_commerce.domain.models.Cart;
import e_commerce.domain.models.CartId;
import e_commerce.domain.models.Product;

import java.util.ArrayList;
import java.util.UUID;

public class UpdateCartService {
    private final CartRepository cartRepository;

    public UpdateCartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void updateCart(UUID id, ArrayList<Product> productList) {
        CartId cartId = new CartId(id.toString());
        Cart cartToUpdate = new Cart(cartId, productList);

        if (!cartRepository.findById(cartId)) throw new CartNotFoundException(cartId.value.toString());

        cartRepository.addProducts(cartToUpdate);
    }
}

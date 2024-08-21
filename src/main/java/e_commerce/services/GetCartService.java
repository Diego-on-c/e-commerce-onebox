package e_commerce.services;

import e_commerce.domain.contracts.CartRepository;
import e_commerce.domain.exceptions.CartNotFoundException;
import e_commerce.domain.models.Cart;
import e_commerce.domain.models.CartId;

import java.util.UUID;

public class GetCartService {
    private final CartRepository cartRepository;

    public GetCartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getCartById(UUID id) {
        CartId cartId = new CartId(id.toString());

        if (!cartRepository.findById(cartId)) throw new CartNotFoundException(cartId.value.toString());

        return cartRepository.getCartById(cartId.value);
    }
}

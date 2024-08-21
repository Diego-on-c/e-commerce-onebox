package e_commerce.services;

import e_commerce.domain.contracts.CartRepository;
import e_commerce.domain.exceptions.CartNotFoundException;
import e_commerce.domain.models.CartId;

import java.util.UUID;

public class DeleteCartService {
    private final CartRepository cartRepository;

    public DeleteCartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void deleteCart(UUID id) throws CartNotFoundException {
        CartId cartId = new CartId(id.toString());
        if (!cartRepository.findById(cartId)) throw new CartNotFoundException(cartId.value.toString());
        cartRepository.deleteCartById(cartId);
    }
}

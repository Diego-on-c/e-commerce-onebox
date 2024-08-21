package e_commerce.repositories;

import e_commerce.domain.contracts.CartRepository;
import e_commerce.domain.models.Cart;
import e_commerce.domain.models.CartId;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryCartRepository implements CartRepository {

    private final ConcurrentHashMap<UUID, Cart> storage;

    public InMemoryCartRepository() {
        this.storage = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<UUID, Cart> getStorage() {
        return storage;
    }

    @Override
    public String addCart(Cart cart) {
        storage.put(cart.getId().value, cart);
        return cart.getId().value.toString();
    }

    @Override
    public Cart getCartById(UUID cartId) {
        return storage.get(cartId);
    }

    @Override
    public boolean findById(CartId cartId) {
        return storage.containsKey(cartId.value);
    }

    @Override
    public void addProducts(Cart cart) {
        storage.put(cart.getId().value, cart);
    }

    @Override
    public void deleteCartById(CartId cartId) {

        storage.remove(cartId.value);
    }

    @Override
    public void truncate() {
        storage.clear();
    }

    @Override
    public int getCartsQuantity() {
        return storage.size();
    }
}

package e_commerce.services;

import e_commerce.domain.contracts.CartRepository;
import e_commerce.domain.exceptions.CartNotFoundException;
import e_commerce.domain.models.Cart;
import e_commerce.domain.models.CartId;
import e_commerce.domain.models.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateCartServiceTest {
    private final CartRepository cartRepository;
    private final UpdateCartService updateCartService;

    UpdateCartServiceTest() {
        this.cartRepository = mock(CartRepository.class);
        this.updateCartService = new UpdateCartService(cartRepository);
    }

    @Test
    void test_should_update_cart() throws CartNotFoundException {
        CartId cartId = new CartId("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        Product product = new Product(123456L, "new product description", 25.00d);
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);

        Cart cart = new Cart(cartId, productList);

        when(cartRepository.findById(any(CartId.class))).thenReturn(true);

        updateCartService.updateCart(cart.getId().value, productList);

        verify(cartRepository).addProducts(any(Cart.class));
    }

    @Test
    void test_should_fail_if_cartId_not_exist() {
        CartId cartId = new CartId("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        Product product = new Product(123456L, "new product description", 25.00d);
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);

        Cart cart = new Cart(cartId, productList);

        when(cartRepository.findById(any(CartId.class))).thenReturn(false);

        assertThrows(CartNotFoundException.class, () -> updateCartService.updateCart(cart.getId().value, productList));
    }
}
package e_commerce.services;

import e_commerce.domain.contracts.CartRepository;
import e_commerce.domain.exceptions.CartNotFoundException;
import e_commerce.domain.models.Cart;
import e_commerce.domain.models.CartId;
import e_commerce.domain.models.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetCartServiceTest {

    private final CartRepository cartRepository;
    private final GetCartService getCartService;

    GetCartServiceTest() {
        this.cartRepository = mock(CartRepository.class);
        this.getCartService = new GetCartService(cartRepository);
    }

    @Test
    void test_should_get_cart_from_repository_by_id() throws CartNotFoundException {
        CartId cartId = new CartId("38400000-8cf0-11bd-b23e-10b96e4ef00d");

        Product product = new Product(123456L, "product description", 25.00d);
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);

        Cart newCart = new Cart(cartId, productList);

        when(cartRepository.findById(any(CartId.class))).thenReturn(true);

        getCartService.getCartById(cartId.value);

        assertEquals(cartId, newCart.getId());
    }

    @Test
    void test_should_fail_if_cartId_not_exist() {
        CartId cartId = new CartId("38400000-8cf0-11bd-b23e-10b96e4ef00d");

        when(cartRepository.findById(any(CartId.class))).thenReturn(false);

        assertThrows(CartNotFoundException.class, () -> getCartService.getCartById(cartId.value));
    }
}
package e_commerce.services;

import e_commerce.domain.contracts.CartRepository;
import e_commerce.domain.exceptions.CartNotFoundException;
import e_commerce.domain.models.CartId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteCartServiceTest {

    private final CartRepository cartRepository;
    private final DeleteCartService deleteCartService;

    DeleteCartServiceTest() {
        this.cartRepository = mock(CartRepository.class);
        this.deleteCartService = new DeleteCartService(cartRepository);
    }

    @Test
    void test_should_delete_cart_from_repository_by_id() throws CartNotFoundException {
        CartId cartId = new CartId("38400000-8cf0-11bd-b23e-10b96e4ef00d");

        when(cartRepository.findById(any(CartId.class))).thenReturn(true);

        deleteCartService.deleteCart(cartId.value);

        verify(cartRepository).deleteCartById(any(CartId.class));
    }

    @Test
    void test_should_fail_if_cartId_not_exist() {
        CartId cartId = new CartId("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        when(cartRepository.findById(cartId)).thenReturn(false);

        assertThrows(CartNotFoundException.class, () -> deleteCartService.deleteCart(cartId.value));
    }
}
package e_commerce.infrastructure.controllers;

import e_commerce.domain.exceptions.CartNotFoundException;
import e_commerce.domain.models.CartId;
import e_commerce.services.DeleteCartService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class CartControllerDeleteTest {

    private final DeleteCartService deleteCartServiceMock = Mockito.mock(DeleteCartService.class);

    private final CartControllerDelete controllerDelete = new CartControllerDelete(deleteCartServiceMock);

    @Test
    void test_should_delete_cart() throws CartNotFoundException {
        CartId cartId = new CartId("38400000-8cf0-11bd-b23e-10b96e4ef00d");

        controllerDelete.deleteCartById(cartId.value);

        verify(deleteCartServiceMock).deleteCart(cartId.value);
    }

    @Test
    public void test_should_fail_when_getCart_fails() throws CartNotFoundException {
        CartId cartId = new CartId("38400000-8cf0-11bd-b23e-10b96e4ef00d");

        doThrow(new CartNotFoundException(cartId.value.toString())).when(deleteCartServiceMock).deleteCart(any());

        assertThrows(CartNotFoundException.class, () -> controllerDelete.deleteCartById(cartId.value));
    }
}
package e_commerce.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import e_commerce.domain.exceptions.CartNotFoundException;
import e_commerce.domain.models.CartId;
import e_commerce.infrastructure.dtos.mappers.CartMapper;
import e_commerce.services.GetCartService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CartControllerGetTest {

    private final GetCartService getCartServiceMock = Mockito.mock(GetCartService.class);
    private final CartMapper cartMapperMock = Mockito.mock(CartMapper.class);
    private final ObjectMapper objectMapperMock = Mockito.mock(ObjectMapper.class);

    private final CartControllerGet cartControllerGet = new CartControllerGet(getCartServiceMock, cartMapperMock, objectMapperMock);

    @Test
    void test_should_fail_when_getCart_fails() throws CartNotFoundException {
        CartId cartId = new CartId("f5ec2a57-1a80-4319-a0f1-94b3e4422672");

        when(getCartServiceMock.getCartById(cartId.value)).thenThrow(new CartNotFoundException(cartId.value.toString()));

        assertThrows(CartNotFoundException.class, () -> cartControllerGet.getCartId(cartId.value));
    }
}
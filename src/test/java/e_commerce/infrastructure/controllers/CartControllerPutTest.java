package e_commerce.infrastructure.controllers;

import e_commerce.domain.exceptions.CartNotFoundException;
import e_commerce.domain.models.Cart;
import e_commerce.domain.models.CartId;
import e_commerce.domain.models.Product;
import e_commerce.infrastructure.dtos.ProductDTO;
import e_commerce.infrastructure.dtos.mappers.ProductMapper;
import e_commerce.services.UpdateCartService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

class CartControllerPutTest {

    private final UpdateCartService updateCartServiceMock = Mockito.mock(UpdateCartService.class);
    private final ProductMapper productMapperMock = Mockito.mock(ProductMapper.class);

    private final CartControllerPut cartControllerPut = new CartControllerPut(updateCartServiceMock, productMapperMock);

    @Test
    void test_should_update_cart() throws CartNotFoundException {
        CartId cartId = new CartId("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        ProductDTO product = new ProductDTO(123456L, "product description", 25.00d);
        ArrayList<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(product);

        cartControllerPut.updateCart(cartId.value, productDTOList);

        ArrayList<Product> productList = productMapperMock.productDTOtoEntity(productDTOList);

        Cart cart = new Cart(cartId, productList);

        verify(updateCartServiceMock).updateCart(cart.getId().value, productList);
    }
}
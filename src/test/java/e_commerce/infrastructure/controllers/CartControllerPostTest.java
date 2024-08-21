package e_commerce.infrastructure.controllers;

import e_commerce.domain.models.Product;
import e_commerce.infrastructure.dtos.ProductDTO;
import e_commerce.infrastructure.dtos.mappers.ProductMapper;
import e_commerce.services.CreateCartService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

class CartControllerPostTest {

    private final CreateCartService createCartServiceMock = Mockito.mock(CreateCartService.class);
    private final ProductMapper productMapperMock = Mockito.mock(ProductMapper.class);

    private final CartControllerPost cartControllerPost = new CartControllerPost(createCartServiceMock, productMapperMock);

    @Test
    void test_if_can_create_cart() {
        ProductDTO product = new ProductDTO(123456L, "product description", 25.00d);
        ArrayList<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(product);

        ArrayList<Product> productList = productMapperMock.productDTOtoEntity(productDTOList);

        cartControllerPost.createCart(productDTOList);

        verify(createCartServiceMock).createCart(any(productList.getClass()));
    }
}
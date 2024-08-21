package e_commerce.services;

import e_commerce.domain.contracts.CartRepository;
import e_commerce.domain.exceptions.InvalidDescriptionException;
import e_commerce.domain.models.Cart;
import e_commerce.domain.models.CartId;
import e_commerce.domain.models.Product;
import e_commerce.services.deleteTaskSchedulerServices.DeleteCartAfterTTLService;
import e_commerce.services.deleteTaskSchedulerServices.DeleteCartTask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateCartServiceTest {

    private final CartRepository cartRepository;
    private final DeleteCartAfterTTLService deleteCartAfterTTLService;
    private final CreateCartService createCartService;

    CreateCartServiceTest() {
        this.cartRepository = mock(CartRepository.class);
        this.deleteCartAfterTTLService = mock(DeleteCartAfterTTLService.class);
        this.createCartService = new CreateCartService(cartRepository, deleteCartAfterTTLService);
    }

    @Test
    void test_should_create_cart() {
        Product product = new Product(123456L, "product description", 25.00d);
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);

        createCartService.createCart(productList);

        verify(cartRepository).addCart(any(Cart.class));
    }
}
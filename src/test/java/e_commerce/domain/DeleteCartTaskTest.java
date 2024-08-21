package e_commerce.domain;

import e_commerce.domain.models.Cart;
import e_commerce.domain.models.CartId;
import e_commerce.domain.models.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCartTaskTest extends IntegrationTest {

    @Test
    void test_should_delete_cart_after_given_TTL() throws InterruptedException {
        Product productA = new Product(123456L, "product description", 25.00d);
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(productA);

        CartId cartId = new CartId();
        Cart cart = new Cart(cartId, productList);

        String newCart = createCartService.createCart(productList);

        assertEquals(1, cartRepository.getCartsQuantity());

        Thread.sleep(4000L);

        assertEquals(0, cartRepository.getCartsQuantity());
    }
}

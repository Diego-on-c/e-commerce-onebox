package e_commerce.services;

import e_commerce.domain.contracts.CartRepository;
import e_commerce.domain.models.Cart;
import e_commerce.domain.models.Product;
import e_commerce.services.deleteTaskSchedulerServices.DeleteCartAfterTTLService;
import e_commerce.services.deleteTaskSchedulerServices.DeleteCartTask;

import java.util.ArrayList;

public class CreateCartService {
    private final CartRepository cartRepository;
    private final DeleteCartAfterTTLService deleteCartAfterTTLService;

    public CreateCartService(CartRepository cartRepository, DeleteCartAfterTTLService deleteCartAfterTTLService) {
        this.cartRepository = cartRepository;
        this.deleteCartAfterTTLService = deleteCartAfterTTLService;
    }

    public String createCart(ArrayList<Product> productList) throws RuntimeException{
        Cart cart = new Cart(productList);

        deleteCartAfterTTLService.scheduleTask(new DeleteCartTask(cart.getId(), cartRepository));

        return cartRepository.addCart(cart);
    }
}

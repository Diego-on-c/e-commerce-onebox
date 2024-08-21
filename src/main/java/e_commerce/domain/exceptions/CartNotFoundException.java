package e_commerce.domain.exceptions;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String id) {
        super("Cart with id =" + id + " not found.");
    }
}
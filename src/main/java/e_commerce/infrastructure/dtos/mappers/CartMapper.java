package e_commerce.infrastructure.dtos.mappers;

import e_commerce.infrastructure.dtos.CartDTO;
import e_commerce.infrastructure.dtos.ProductDTO;
import e_commerce.domain.exceptions.InvalidDescriptionException;
import e_commerce.domain.models.Cart;
import e_commerce.domain.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartDTO cartToCartDTO(Cart cart) {
        if (cart == null) {
            return null;
        }
        CartDTO cartDTO = new CartDTO();
        ArrayList<ProductDTO> productDTOList = productEntityToDTO(cart.getProducts());
        cartDTO.setProductList(productDTOList);
        return cartDTO;
    }

    private ArrayList<ProductDTO> productEntityToDTO(ArrayList<Product> productList) {
        return productList.stream()
                .map(product -> {
                    try {
                        return mapProductFromDTO(product);
                    } catch (InvalidDescriptionException e) {
                        throw new RuntimeException(e.getMessage(), e.initCause(e.getCause()));
                    }
                }).collect(Collectors.toCollection(ArrayList::new));
    }

    private ProductDTO mapProductFromDTO(Product product) throws InvalidDescriptionException {
        return new ProductDTO(
                product.getId(),
                product.getDescription(),
                product.getAmount());
    }
}

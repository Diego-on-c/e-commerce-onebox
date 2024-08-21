package e_commerce.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import e_commerce.infrastructure.dtos.CartDTO;
import e_commerce.infrastructure.dtos.mappers.CartMapper;
import e_commerce.services.GetCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class CartControllerGet {

    CartMapper cartMapper;
    ObjectMapper objectMapper;
    GetCartService getCartService;

    public CartControllerGet(GetCartService getCartService, CartMapper cartMapper, ObjectMapper objectMapper) {
        this.cartMapper = cartMapper;
        this.getCartService = getCartService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCartId(@PathVariable UUID id) {
        CartDTO response = cartMapper.cartToCartDTO(getCartService.getCartById(id));
        return ResponseEntity.ok()
                .header("content-type", "application/json")
                .body("{" +
                        "\"cart-ID\": \"" + id + "\"," +
                        "\"products\": " + mapToString(response) +
                        "}");
    }

    private String mapToString(CartDTO cart) {
        try {
            return objectMapper.writeValueAsString(cart.getProductList());
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

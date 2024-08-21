package e_commerce.infrastructure.controllers;

import e_commerce.infrastructure.dtos.ProductDTO;
import e_commerce.domain.models.Product;
import e_commerce.infrastructure.dtos.mappers.ProductMapper;
import e_commerce.services.CreateCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class CartControllerPost {

    ProductMapper productMapper;
    CreateCartService createCartService;

    public CartControllerPost(CreateCartService createCartService, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.createCartService = createCartService;
    }

    @PostMapping("/newCart")
    public ResponseEntity<String> createCart(@RequestBody ArrayList<ProductDTO> productDTOList) {
        ArrayList<Product> productList = productMapper.productDTOtoEntity(productDTOList);
        String cartId = createCartService.createCart(productList);
        return ResponseEntity.ok().header("content-type", "application/json")
                .body("{" +
                        "\"result\"= \"Cart created\"," +
                        "\"cart-ID\"= \"" + cartId + "\"," +
                        "}");
    }
}
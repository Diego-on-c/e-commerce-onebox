package e_commerce.infrastructure.controllers;

import e_commerce.domain.models.Product;
import e_commerce.infrastructure.dtos.ProductDTO;
import e_commerce.infrastructure.dtos.mappers.ProductMapper;
import e_commerce.services.UpdateCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class CartControllerPut {

    ProductMapper productMapper;
    UpdateCartService updateCartService;

    public CartControllerPut(UpdateCartService updateCartService, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.updateCartService = updateCartService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCart(@PathVariable UUID id, @RequestBody ArrayList<ProductDTO> productDTOList) {
        ArrayList<Product> productList = productMapper.productDTOtoEntity(productDTOList);
        updateCartService.updateCart(id, productList);
        return ResponseEntity.ok().header("content-type","application/json")
                .body("{" +
                        "\"result\": \"cart updated correctly\""+
                        "}");
    }
}

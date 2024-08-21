package e_commerce.infrastructure.controllers;

import e_commerce.services.DeleteCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class CartControllerDelete {

    DeleteCartService deleteCartService;

    public CartControllerDelete(DeleteCartService deleteCartService) {
        this.deleteCartService = deleteCartService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartById(@PathVariable UUID id) {
        deleteCartService.deleteCart(id);
        return ResponseEntity.ok().header("content-type", "application/json")
                .body("{" +
                        "\"message\": \"Cart id= " + id + " deleted\"" +
                        "}");
    }
}

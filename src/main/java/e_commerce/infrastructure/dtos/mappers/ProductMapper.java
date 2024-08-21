package e_commerce.infrastructure.dtos.mappers;

import e_commerce.infrastructure.dtos.ProductDTO;
import e_commerce.domain.exceptions.InvalidDescriptionException;
import e_commerce.domain.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ArrayList<Product> productDTOtoEntity(ArrayList<ProductDTO> productDTOList) {
        return productDTOList.stream()
                .map(productDTO -> {
                    try {
                        return mapProductFromDTO(productDTO);
                    } catch (InvalidDescriptionException e) {
                        throw new RuntimeException(e.getMessage(), e.initCause(e.getCause()));
                    }
                }).collect(Collectors.toCollection(ArrayList::new));
    }

    private Product mapProductFromDTO(ProductDTO productDto) throws InvalidDescriptionException {
        return new Product(
                productDto.getId(),
                productDto.getDescription(),
                productDto.getAmount());
    }
}

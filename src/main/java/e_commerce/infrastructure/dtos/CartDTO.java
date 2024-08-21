package e_commerce.infrastructure.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = {ACCEPT_CASE_INSENSITIVE_PROPERTIES})
public class CartDTO {

    @JsonProperty(value = "productList")
    private ArrayList<ProductDTO> productList;

    public CartDTO(ArrayList<ProductDTO> productList) {
        this.productList = productList;
    }

    public CartDTO() {
    }

    public ArrayList<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<ProductDTO> productList) {
        this.productList = productList;
    }
}

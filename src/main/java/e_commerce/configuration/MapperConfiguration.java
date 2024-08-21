package e_commerce.configuration;

import e_commerce.infrastructure.dtos.mappers.CartMapper;
import e_commerce.infrastructure.dtos.mappers.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    ProductMapper productMapper() {
        return new ProductMapper();
    }
    @Bean
    CartMapper cartMapper() {
        return new CartMapper();
    }
}

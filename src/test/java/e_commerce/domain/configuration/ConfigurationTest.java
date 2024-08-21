package e_commerce.domain.configuration;

import e_commerce.domain.contracts.CartRepository;
import e_commerce.repositories.InMemoryCartRepository;
import e_commerce.services.CreateCartService;
import e_commerce.services.deleteTaskSchedulerServices.DeleteCartAfterTTLService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("integration-tests")
public class ConfigurationTest {
    @Value("${ttl}")
    private Long cartsTimeToLive;

    @Bean("deleteCartSchedulerForTest")
    DeleteCartAfterTTLService deleteCartAfterTTLService() {
        return new DeleteCartAfterTTLService(cartsTimeToLive);
    }

    @Bean("cartRepositoryForTest")
    CartRepository cartRepository() {
        return new InMemoryCartRepository();
    }

    @Bean("createCartForTest")
    CreateCartService createCartService(CartRepository cartRepository, DeleteCartAfterTTLService deleteCartAfterTTLService) {
        return new CreateCartService(cartRepository, deleteCartAfterTTLService);
    }
}

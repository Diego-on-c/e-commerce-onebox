package e_commerce.configuration;

import e_commerce.domain.contracts.CartRepository;
import e_commerce.repositories.InMemoryCartRepository;
import e_commerce.services.CreateCartService;
import e_commerce.services.DeleteCartService;
import e_commerce.services.GetCartService;
import e_commerce.services.UpdateCartService;
import e_commerce.services.deleteTaskSchedulerServices.DeleteCartAfterTTLService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfiguration {

    @Bean
    CartRepository cartRepository() {
        return new InMemoryCartRepository();
    }

    @Bean
    CreateCartService createCartService(CartRepository cartRepository, DeleteCartAfterTTLService deleteCartAfterTTLService) {
        return new CreateCartService(cartRepository, deleteCartAfterTTLService);
    }

    @Bean
    GetCartService getCartService(CartRepository cartRepository) {
        return new GetCartService(cartRepository);
    }

    @Bean
    UpdateCartService updateCartService(CartRepository cartRepository){
        return new UpdateCartService(cartRepository);
    }

    @Bean
    DeleteCartService deleteCartService(CartRepository cartRepository){
        return new DeleteCartService(cartRepository);
    }


}

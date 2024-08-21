package e_commerce.configuration;

import e_commerce.services.deleteTaskSchedulerServices.DeleteCartAfterTTLService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfiguration {
    @Value("${ttl}")
    private Long cartsTimeToLive;

    @Bean
    DeleteCartAfterTTLService deleteCartAfterTTLService() {
        return new DeleteCartAfterTTLService(cartsTimeToLive);
    }
}

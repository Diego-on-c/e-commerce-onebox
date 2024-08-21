package e_commerce.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import e_commerce.ECommerceApplication;
import e_commerce.domain.configuration.ConfigurationTest;
import e_commerce.domain.contracts.CartRepository;
import e_commerce.services.CreateCartService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;

@SpringBootTest(
        classes = {ECommerceApplication.class},
        properties = {"spring.profiles.active=integration-tests"}
)
@AutoConfigureMockMvc
@ContextConfiguration(classes = ConfigurationTest.class)
@TestPropertySource(locations = "/application-integration-tests.properties")
public class IntegrationTest {

    @Autowired
    public Environment env;

    @Autowired
    public MockMvc mvc;

    @Autowired
    public CreateCartService createCartService;

    @Autowired
    public CartRepository cartRepository;

    @BeforeEach
    void setUp() {
        mockMvc(mvc);
        cartRepository.truncate();
    }
}

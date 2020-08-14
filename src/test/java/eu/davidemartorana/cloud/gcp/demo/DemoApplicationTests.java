package eu.davidemartorana.cloud.gcp.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class DemoApplicationTests {

    @Container
    private static final PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:12")
            .withDatabaseName("poc");

    @DynamicPropertySource
    public static void init(final DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("app.datasources.first.url", postgresqlContainer::getJdbcUrl);
        propertyRegistry.add("PG_USER", postgresqlContainer::getUsername);
        propertyRegistry.add("PG_PWD", postgresqlContainer::getPassword);
    }


    @Test
    void contextLoads() {
    }

}

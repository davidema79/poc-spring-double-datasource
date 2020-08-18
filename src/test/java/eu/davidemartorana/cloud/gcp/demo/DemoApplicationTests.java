package eu.davidemartorana.cloud.gcp.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers
class DemoApplicationTests {

    @Container
    private static final PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:12")
            .withDatabaseName("poc");

    @Container
    private static final MySQLContainer mysqlContainer = new MySQLContainer("mysql:8")
            .withDatabaseName("poc");

    @DynamicPropertySource
    public static void init(final DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.jdbc-url", postgresqlContainer::getJdbcUrl);
        propertyRegistry.add("PG_USER", postgresqlContainer::getUsername);
        propertyRegistry.add("PG_PWD", postgresqlContainer::getPassword);

        propertyRegistry.add("spring.warehouse-datasource.jdbc-url", mysqlContainer::getJdbcUrl);
        propertyRegistry.add("MYSQL_USER", mysqlContainer::getUsername);
        propertyRegistry.add("MYSQL_PWD", mysqlContainer::getPassword);
    }


    @Test
    void contextLoads() {
    }

}

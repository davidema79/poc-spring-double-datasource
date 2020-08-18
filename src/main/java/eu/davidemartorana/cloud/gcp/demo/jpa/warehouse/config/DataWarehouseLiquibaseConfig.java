package eu.davidemartorana.cloud.gcp.demo.jpa.warehouse.config;

import eu.davidemartorana.cloud.gcp.demo.jpa.DataLayerUtils;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@ConditionalOnProperty(value = "spring.warehouse-datasource.liquibase.enabled", havingValue = "true")
public class DataWarehouseLiquibaseConfig {

    @Bean(name = "warehouseLiquibaseProperties")
    @ConfigurationProperties(prefix = "spring.warehouse-datasource.liquibase")
    public LiquibaseProperties warehouseLiquibaseProperties(){
        return new LiquibaseProperties();
    }

    @Bean(name = "warehouseSpringLiquibase")
    public SpringLiquibase warehouseSpringLiquibase(@Qualifier("warehouseDataSource") final DataSource dataSource,
                                                    @Qualifier("warehouseLiquibaseProperties") final LiquibaseProperties liquibaseProperties) {
        return DataLayerUtils.createAndInitialise(dataSource, liquibaseProperties);
    }

}

package eu.davidemartorana.cloud.gcp.demo.jpa.relational.config;

import eu.davidemartorana.cloud.gcp.demo.jpa.DataLayerUtils;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@ConditionalOnProperty(value = "spring.datasource.liquibase.enabled", havingValue = "true")
public class RelationalLiquibaseConfig {

    @Bean(name = "relationalLiquibaseProperties")
    @ConfigurationProperties(prefix = "spring.datasource.liquibase")
    public LiquibaseProperties relationalLiquibaseProperties(){
        return new LiquibaseProperties();
    }

    @Bean(name = "relationalSpringLiquibase")
    @Primary
    public SpringLiquibase relationalSpringLiquibase(@Qualifier("relationalDataSource") final DataSource dataSource,
                                                     @Qualifier("relationalLiquibaseProperties") final LiquibaseProperties liquibaseProperties) {
        return DataLayerUtils.createAndInitialise(dataSource, liquibaseProperties);
    }

}

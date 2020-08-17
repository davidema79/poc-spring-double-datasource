package eu.davidemartorana.cloud.gcp.demo.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({PostgreSQLConfigProps.class,MySQLConfigProps.class})

public class JPAConfig {


    @Bean
    public DataSource postgresDataSource() {
        final DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.build();
    }

}

package eu.davidemartorana.cloud.gcp.demo.jpa.relational.config;

import com.zaxxer.hikari.HikariDataSource;
import eu.davidemartorana.cloud.gcp.demo.jpa.relational.entities.Component;
import eu.davidemartorana.cloud.gcp.demo.jpa.relational.repos.ComponentsRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Import(RelationalLiquibaseConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "relationalEntityManagerFactory",
        transactionManagerRef = "relationalTransactionManager",
        basePackageClasses = {ComponentsRepository.class, Component.class}
)
public class RelationalDBJpaConfig {

    @Primary
    @Bean(name = "relationalDataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource relationalDataSource(){
        final HikariDataSource ds = DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();

        return ds;
    }

    @Primary
    @Bean(name = "relationalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean relationalEntityManagerFactory(
            final EntityManagerFactoryBuilder builder,
            @Qualifier("relationalDataSource") final DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .persistenceUnit("rdbms-unit")
                .packages(Component.class)
                .build();
    }

    @Primary
    @Bean(name = "relationalTransactionManager")
    public JpaTransactionManager relationalTransactionManager(@Qualifier("relationalEntityManagerFactory") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}

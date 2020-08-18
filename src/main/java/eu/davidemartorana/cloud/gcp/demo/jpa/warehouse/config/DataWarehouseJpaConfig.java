package eu.davidemartorana.cloud.gcp.demo.jpa.warehouse.config;

import eu.davidemartorana.cloud.gcp.demo.jpa.warehouse.entities.UserComponentVisit;
import eu.davidemartorana.cloud.gcp.demo.jpa.warehouse.repos.UserComponentVisitRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "warehouseEntityManagerFactory",
        transactionManagerRef = "warehouseTransactionManager",
        basePackageClasses = UserComponentVisitRepo.class
)
public class DataWarehouseJpaConfig {

    @Bean(name = "warehouseDataSource")
    @ConfigurationProperties(prefix="spring.warehouse-datasource")
    public DataSource warehouseDataSource(){
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean(name = "warehouseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean warehouseEntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                              @Qualifier("warehouseDataSource") final DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(UserComponentVisit.class)
                .persistenceUnit("data-warehouse-unit")
                .build();
    }

    @Bean(name = "warehouseTransactionManager")
    public JpaTransactionManager warehouseTransactionManager(@Qualifier("warehouseEntityManagerFactory") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}

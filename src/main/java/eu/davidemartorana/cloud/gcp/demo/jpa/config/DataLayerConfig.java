package eu.davidemartorana.cloud.gcp.demo.jpa.config;

import eu.davidemartorana.cloud.gcp.demo.jpa.relational.config.RelationalDBJpaConfig;
import eu.davidemartorana.cloud.gcp.demo.jpa.warehouse.config.DataWarehouseJpaConfig;
import org.springframework.context.annotation.Import;

@Import({RelationalDBJpaConfig.class, DataWarehouseJpaConfig.class})
public class DataLayerConfig {
}

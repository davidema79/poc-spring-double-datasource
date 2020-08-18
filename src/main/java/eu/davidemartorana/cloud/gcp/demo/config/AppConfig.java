package eu.davidemartorana.cloud.gcp.demo.config;


import eu.davidemartorana.cloud.gcp.demo.jpa.config.DataLayerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataLayerConfig.class)
public class AppConfig {
}

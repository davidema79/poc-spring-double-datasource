package eu.davidemartorana.cloud.gcp.demo.jpa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.datasource.first")
public class PostgreSQLConfigProps extends AbstractDataSourceProperties{
}

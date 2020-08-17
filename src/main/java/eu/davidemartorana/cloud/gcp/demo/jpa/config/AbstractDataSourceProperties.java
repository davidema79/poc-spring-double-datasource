package eu.davidemartorana.cloud.gcp.demo.jpa.config;


import eu.davidemartorana.cloud.gcp.demo.model.Credentials;

public abstract class AbstractDataSourceProperties {

    //    driver-class-name: org.postgresql.Driver
//    url: jdbc:postgresql://${app.datasources.first.host}:${app.datasources.first.port}/${app.datasources.first.name}?currentSchema=${app.datasources.first.schema}



    private String ulr;

    private String driverClassName;

    private Credentials credentials;



}

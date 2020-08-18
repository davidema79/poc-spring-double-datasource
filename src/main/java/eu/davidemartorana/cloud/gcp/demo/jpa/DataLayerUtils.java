package eu.davidemartorana.cloud.gcp.demo.jpa;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;

import javax.sql.DataSource;

public final class DataLayerUtils {

    private DataLayerUtils() throws IllegalAccessException {
        throw new IllegalAccessException("This class cannot be instantiated at runtime.");
    }

    public static SpringLiquibase createAndInitialise(final DataSource dataSource, final LiquibaseProperties liquibaseProperties) {
        final SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog(liquibaseProperties.getChangeLog());

        springLiquibase.setChangeLog(liquibaseProperties.getChangeLog());
        springLiquibase.setClearCheckSums(liquibaseProperties.isClearChecksums());
        springLiquibase.setContexts(liquibaseProperties.getContexts());
        springLiquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        springLiquibase.setLiquibaseSchema(liquibaseProperties.getLiquibaseSchema());
        springLiquibase.setLiquibaseTablespace(liquibaseProperties.getLiquibaseTablespace());
        springLiquibase.setDatabaseChangeLogTable(liquibaseProperties.getDatabaseChangeLogTable());
        springLiquibase.setDatabaseChangeLogLockTable(liquibaseProperties.getDatabaseChangeLogLockTable());
        springLiquibase.setDropFirst(liquibaseProperties.isDropFirst());
        springLiquibase.setShouldRun(liquibaseProperties.isEnabled());
        springLiquibase.setLabels(liquibaseProperties.getLabels());
        springLiquibase.setChangeLogParameters(liquibaseProperties.getParameters());
        springLiquibase.setRollbackFile(liquibaseProperties.getRollbackFile());
        springLiquibase.setTestRollbackOnUpdate(liquibaseProperties.isTestRollbackOnUpdate());
        springLiquibase.setTag(liquibaseProperties.getTag());

        return springLiquibase;
    }

}

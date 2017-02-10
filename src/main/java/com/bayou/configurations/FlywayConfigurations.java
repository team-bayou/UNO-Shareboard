package com.bayou.configurations;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by joshuaeaton on 2/10/17.
 */
@Configuration
public class FlywayConfigurations {

    @Bean
    public FlywayMigrationStrategy migrationStrategy() {

        FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
            @Override
            public void migrate(Flyway flyway) {
                flyway.setLocations("classpath:db/migration");
                flyway.setSchemas("shareboard");
                flyway.migrate();
            }
        };

        return strategy;
    }
}

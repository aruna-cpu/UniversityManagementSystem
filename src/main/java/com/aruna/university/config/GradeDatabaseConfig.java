package com.aruna.university.config;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.aruna.university.grade.repository",
        entityManagerFactoryRef = "gradeEntityManagerFactory", transactionManagerRef = "gradeTransactionManager")
public class GradeDatabaseConfig {
    @Bean
    @ConfigurationProperties("app.datasource.grade")
    public DataSourceProperties gradeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "gradeDataSource")
    public DataSource gradeDataSource() {
        return gradeDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "gradeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean gradeEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                            @Qualifier("gradeDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.aruna.university.grade.entity").persistenceUnit("grade").build();
    }

    @Bean(name = "gradeTransactionManager")
    public PlatformTransactionManager gradeTransactionManager(@Qualifier("gradeEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}

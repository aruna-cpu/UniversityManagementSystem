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
@EnableJpaRepositories(basePackages = "com.aruna.university.course.repository",
        entityManagerFactoryRef = "courseEntityManagerFactory", transactionManagerRef = "courseTransactionManager")
public class CourseDatabaseConfig {
    @Bean
    @ConfigurationProperties("app.datasource.course")
    public DataSourceProperties courseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "courseDataSource")
    public DataSource courseDataSource() {
        return courseDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "courseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean courseEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                             @Qualifier("courseDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.aruna.university.course.entity").persistenceUnit("course").build();
    }

    @Bean(name = "courseTransactionManager")
    public PlatformTransactionManager courseTransactionManager(@Qualifier("courseEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}

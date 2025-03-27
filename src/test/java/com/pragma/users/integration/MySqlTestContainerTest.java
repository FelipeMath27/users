package com.pragma.users.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MySqlTestContainerTest {

    @Container
    public static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.3.0")
            .withDatabaseName("test")
            .withUsername("root")
            .withPassword("root");

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
    }

    @Test
    void testMySQLContainerStarts() {
        // Verifica que el contenedor esté en ejecución
        assertThat(mysqlContainer.isRunning()).isTrue();

        // Opcional: Imprime la URL JDBC para verificar la conexión
        System.out.println("JDBC URL: " + mysqlContainer.getJdbcUrl());
        System.out.println("Username: " + mysqlContainer.getUsername());
        System.out.println("Password: " + mysqlContainer.getPassword());
    }
}
package com.pragma.talentpool.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MyContainerTest {

    @Autowired
    private MyRepository myRepository;

    @Test
    void testRepository() {
        MyEntity entity = new MyEntity();
        entity.setName("Test");
        myRepository.save(entity);

        assertThat(myRepository.findByName("Test")).isNotNull();
    }
}
package com.pragma.talentpool;

import org.springframework.boot.SpringApplication;

public class TestTalentpoolApplication {

	public static void main(String[] args) {
		SpringApplication.from(TalentpoolApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

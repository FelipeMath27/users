package com.pragma.users;

import com.pragma.users.domain.spi.IRolPersistencePort;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.infrastructure.security.IPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
public class UsersApplication implements CommandLineRunner {

	private final IPasswordService iPasswordService;
	private final IUserPersistencePort userPersistencePort;
	private final IRolPersistencePort iRolPersistencePort;

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}

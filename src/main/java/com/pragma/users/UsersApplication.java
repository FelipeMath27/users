package com.pragma.users;

import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.TypeDocumentEnum;
import com.pragma.users.domain.model.TypeRolEnum;
import com.pragma.users.domain.model.User;
import com.pragma.users.domain.spi.IRolPersistencePort;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.infrastructure.security.IPasswordService;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;


@SpringBootApplication
@RequiredArgsConstructor
public class UsersApplication implements CommandLineRunner {

	private final IPasswordService iPasswordService;
	private final IUserPersistencePort userPersistencePort;
	private final IRolPersistencePort iRolPersistencePort;

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		SpringApplication.run(UsersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
}

package com.home.helpdesk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.home.helpdesk.api.entity.User;
import com.home.helpdesk.api.enums.ProfileEnum;
import com.home.helpdesk.api.repository.UserRespository;

@SpringBootApplication
public class HelpDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}
	
	
	//metodo auxilia para chamar a inicialização de usuário
	@Bean
	CommandLineRunner init(UserRespository userRespository, PasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(userRespository, passwordEncoder);
		};
	}
	
	//Inicialisar usuário
	private void initUsers(UserRespository userRepository, PasswordEncoder passwordEncoder) {
		User admin = new User();
		admin.setEmail("admin@helpdesk.com");
		admin.setPassword(passwordEncoder.encode("123456"));
		admin.setProfile(ProfileEnum.ROLE_ADMIN);
		
		User find = userRepository.findByEmail("admin@helpdesk.com");
		if(find  == null) {
			userRepository.save(admin);
		}
	}
}

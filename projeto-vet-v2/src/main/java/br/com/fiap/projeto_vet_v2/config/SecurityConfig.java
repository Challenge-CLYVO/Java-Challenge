package br.com.fiap.projeto_vet_v2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		 http.authorizeHttpRequests(
				 
				 auth -> auth.requestMatchers(
						 "/login",
						 "/cadastro",
						 "/css/**",
						 "/js/**",
						 "/img/**"
						 ).permitAll()
				 
				 .requestMatchers("/admin/**")
				 .hasAuthority("ADMIN")
				 
				 .requestMatchers("/responsaval/**")
				 .hasAuthority("RESPONSAVEL")
				 
				 .requestMatchers("/veterinario/**")
				 .hasAuthority("VETERINARIO")
				 
				 .anyRequest()
				 .authenticated()
				 
				 
				 
				 )
		 		.formLogin(form -> form
		 				.loginPage("/login")
		 				.usernameParameter("email")
		 				.defaultSuccessUrl("/", true)
		 				.permitAll()
		 				)
		 		
		 		.logout(logout -> logout
		 				.logoutSuccessUrl("/login?logout")
		 				.permitAll()
		 				);
		return http.build();
		 
		 
	 }
	 
}

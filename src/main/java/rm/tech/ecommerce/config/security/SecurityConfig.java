package rm.tech.ecommerce.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import org.springframework.security.web.SecurityFilterChain;


import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

import lombok.AllArgsConstructor;
import rm.tech.ecommerce.properties.EcommerceProp;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

	private EcommerceProp ecommerceProp;
    
    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(HttpMethod.POST, "/v1/accounts/access/create").permitAll()
				.requestMatchers(HttpMethod.POST, "/v1/accounts/access/login").permitAll()
				.requestMatchers("/swagger").permitAll()
				.requestMatchers("/swagger-ui.html").permitAll()
				.requestMatchers("/swagger-ui/**").permitAll()
				.requestMatchers("/swagger-resources/**").permitAll()
				.requestMatchers("/webjars/**").permitAll()
				.requestMatchers("/api-docs/**").permitAll()
				.requestMatchers("/v1/api-docs/**").permitAll()
				.requestMatchers("/v2/api-docs/**").permitAll()
				.requestMatchers("/v3/api-docs/**").permitAll()
				.anyRequest().authenticated())
				.csrf(csrf -> csrf.disable())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtEncoder jwtEncoder(){
		JWK jwk = new RSAKey.Builder(ecommerceProp.getSecurity().getPublicKey())
		.privateKey(ecommerceProp.getSecurity().getPrivateKey())
		.build();

		var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));

		return new NimbusJwtEncoder(jwks);
	}

	@Bean
	public JwtDecoder jwtDecoder(){
		return NimbusJwtDecoder.withPublicKey(ecommerceProp.getSecurity().getPublicKey()).build();
	}

	
}

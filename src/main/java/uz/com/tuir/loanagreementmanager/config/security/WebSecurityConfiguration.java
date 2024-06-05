package uz.com.tuir.loanagreementmanager.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.DelegatingJwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

	@Value("${keycloak.client-id}")
	private String kcClientId;

	@Value("${keycloak.issuer-url}")
	private String tokenIssuerUrl;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationEntryPoint entryPoint,
			CustomAccessDenied accessDenied) throws Exception {

		DelegatingJwtGrantedAuthoritiesConverter authoritiesConverter = new DelegatingJwtGrantedAuthoritiesConverter(
				new JwtGrantedAuthoritiesConverter(), new KeycloakJwtRolesConverter(kcClientId));

		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
				.httpBasic(AbstractHttpConfigurer::disable)
				.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
					httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(entryPoint);
					httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(accessDenied);
				}).csrf(AbstractHttpConfigurer::disable).oauth2ResourceServer(
						httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
								.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(
										jwt -> new JwtAuthenticationToken(jwt, authoritiesConverter.convert(jwt)))));
		return http.build();
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		return JwtDecoders.fromIssuerLocation(tokenIssuerUrl);
	}

	@Bean
	GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("");
	}
}

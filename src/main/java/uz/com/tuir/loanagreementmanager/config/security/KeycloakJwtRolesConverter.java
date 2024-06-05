package uz.com.tuir.loanagreementmanager.config.security;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static uz.com.tuir.loanagreementmanager.constant.KeycloakConstant.*;

@Log4j2
public class KeycloakJwtRolesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
	private final String kcClientId;

	public KeycloakJwtRolesConverter(String kcClientId) {
		this.kcClientId = kcClientId;
	}

	@Override
	public Collection<GrantedAuthority> convert(@NonNull Jwt jwt) {
		return Stream.concat(realmRoles(jwt).stream(), clientRoles(jwt, List.of("role-agreement-manager")).stream())
				.map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
	}

	@SuppressWarnings("unchecked")
	protected List<String> realmRoles(Jwt jwt) {
		return Optional.ofNullable(jwt.getClaimAsMap(CLAIM_REALM_ACCESS))
				.map(realmAccess -> (List<String>) realmAccess.get(CLAIM_ROLES)).orElse(emptyList());
	}

	@SuppressWarnings("unchecked")
	protected List<String> clientRoles(Jwt jwt, List<String> resource) {
		if (ObjectUtils.isEmpty(resource)) {
			return emptyList();
		}
		Map<String, Object> resourceAccess = jwt.getClaimAsMap(CLAIM_RESOURCE_ACCESS);
		List<String> access = new ArrayList<>();
		if (resourceAccess != null)
			resource.forEach(res -> {
				Map<String, List<String>> clientAccess = (Map<String, List<String>>) resourceAccess.get(res);
				if (clientAccess != null) {
					access.addAll(clientAccess.get(CLAIM_ROLES));
				}
			});
		return access;
	}
}

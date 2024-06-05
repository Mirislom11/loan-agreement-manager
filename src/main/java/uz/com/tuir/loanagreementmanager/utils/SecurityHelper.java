package uz.com.tuir.loanagreementmanager.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import uz.com.tuir.loanagreementmanager.constant.ClaimsType;

@Component
public class SecurityHelper {
    public String getCurrentUserName() {
        return getClaimValue(ClaimsType.USERNAME.getValue());
    }
    public String getCurrentJTI() {
        return getClaimValue("jti");
    }

    private String getClaimValue(String claimName) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken) {
            return ((JwtAuthenticationToken) authentication).getToken().getClaim(claimName);
        }

        if (authentication instanceof AnonymousAuthenticationToken) {
            return StringUtils.EMPTY;
        }

        throw new IllegalStateException("Unsupported authentication token type: " + authentication.getClass());
    }
}

package kg.megacom.portal.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SpnegoAuthenticationToken extends AbstractAuthenticationToken {

    private final String spnegoToken;
    private Object principal;

    public SpnegoAuthenticationToken(String spnegoToken) {
        super(null);
        this.spnegoToken = spnegoToken;
        this.setAuthenticated(false);
    }

    public SpnegoAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.spnegoToken = null;
        this.principal = principal;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return spnegoToken;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}


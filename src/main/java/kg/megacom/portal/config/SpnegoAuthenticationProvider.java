package kg.megacom.portal.config;

import org.ietf.jgss.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Base64;
import java.util.List;

public class SpnegoAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String spnegoToken = (String) authentication.getCredentials();
        byte[] tokenBytes = Base64.getDecoder().decode(spnegoToken);

        try {
            String spn = "HTTP@localhost";
            GSSManager manager = GSSManager.getInstance();
            GSSName serverName = manager.createName(spn, GSSName.NT_HOSTBASED_SERVICE);
//            GSSName serverName = manager.createName("HTTP@localhost", GSSName.NT_HOSTBASED_SERVICE);
            GSSCredential serverCreds = manager.createCredential(serverName,
                    GSSCredential.INDEFINITE_LIFETIME,
                    (Oid) null,
                    GSSCredential.ACCEPT_ONLY);

            GSSContext context = manager.createContext(serverCreds);
            context.acceptSecContext(tokenBytes, 0, tokenBytes.length);

            if (context.isEstablished()) {
                String username = context.getSrcName().toString();
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

                return new SpnegoAuthenticationToken(username, authorities);
            } else {
                throw new BadCredentialsException("SPNEGO context not established.");
            }

        } catch (GSSException e) {
            throw new BadCredentialsException("SPNEGO auth failed", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SpnegoAuthenticationToken.class.isAssignableFrom(authentication);
    }
}


package rm.tech.ecommerce.config.security;

import java.io.IOException;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import rm.tech.ecommerce.module.account.access.services.UserDetailsServiceImpl;

@Component
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class SecurityFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl autenticationService;
    private final JwtDecoder decoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenJWT = getToken(request);

        if (tokenJWT != null) {
            
            Jwt jwt = decoder.decode(tokenJWT);
            String subject = jwt.getClaim("sub");
            UserDetails usuario = autenticationService.loadUserByUsername(subject);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }



        filterChain.doFilter(request, response);
    }

    private String getToken (HttpServletRequest request){

        String authorization = request.getHeader("Authorization");

        if (authorization == null) {
            return null;
        }

        return authorization.replace("Bearer ", "");
    }
    
}
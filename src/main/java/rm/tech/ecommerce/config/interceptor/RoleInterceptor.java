// package rm.tech.ecommerce.config.interceptor;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Map;

// import org.springframework.security.access.AccessDeniedException;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.oauth2.jwt.Jwt;
// import org.springframework.security.oauth2.jwt.JwtDecoder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.method.HandlerMethod;
// import org.springframework.web.servlet.HandlerInterceptor;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;

// @Component
// @RequiredArgsConstructor
// public class RoleInterceptor implements HandlerInterceptor {

//     private final JwtDecoder decoder;
    
//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//         if (handler instanceof HandlerMethod) {
//             HandlerMethod handlerMethod = (HandlerMethod) handler;
//             PreAuthorize preAuthorize = handlerMethod.getMethodAnnotation(PreAuthorize.class);
//             if (preAuthorize != null) {
//                 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                 if (authentication == null || !authentication.isAuthenticated()) {
//                     throw new AccessDeniedException("Acesso negado");
//                 }

//                 String[] allowedRoles = extractRoles(preAuthorize.value());

//                 Jwt jwt = (Jwt) authentication.getPrincipal();
                
//                 Map<String, Object> claims = extractClaimsFromJwt(jwt.getTokenValue());
//                 List<String> userRoles = extractUserRoles(claims);

//                 for (String allowedRole : allowedRoles) {
//                     if (userRoles.contains(allowedRole.replace("ROLE_", ""))) {
//                         return true;
//                     }
//                 }

//                 // throw new AccessDeniedException("Acesso negado");
//             }
//         }
//         return true;
//     }

//     private String[] extractRoles(String expression) {
//         String[] parts = expression.replaceAll("hasAnyAuthority\\('(.*)'\\)", "$1").split(",");
//         for (int i = 0; i < parts.length; i++) {
//             parts[i] = parts[i].replaceAll("'", "").trim();
//         }
//         return parts;
//     }

//     private Map<String, Object> extractClaimsFromJwt(String jwtToken) {
//         Jwt jwt = decoder.decode(jwtToken);
//         return jwt.getClaims();
//     }

//     private List<String> extractUserRoles(Map<String, Object> claims){
//         String roles = (String) claims.get("role");
//         return Arrays.asList(roles.split(" "));
//     }
// }

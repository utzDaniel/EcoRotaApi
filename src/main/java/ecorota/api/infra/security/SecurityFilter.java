package ecorota.api.infra.security;

import ecorota.api.repository.UsuarioRepository;
import ecorota.api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static Map<String, HttpMethod> urls = new HashMap<>(Map.of(
            "/login", HttpMethod.POST,
            "/usuario", HttpMethod.POST,
            "/dominio", HttpMethod.GET));


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = this.getToken(request);

        if (Objects.nonNull(tokenJWT)) {
            var subject = this.tokenService.getSubject(tokenJWT);
            var usuario = this.usuarioRepository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else
            System.err.println("SEM TOKEN");
        //throw new JWTVerificationException("SEM TOKEN");


        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {

        var key = urls.keySet().stream().filter(v -> request.getRequestURL().toString().contains(v)).findFirst();
        if (key.isPresent()) {
            if (urls.get(key.get()).name().equals(request.getMethod())) return null;
        }
        var authorizationHeader = request.getHeader("Authorization");
        if (Objects.isNull(authorizationHeader)) return null;
        return authorizationHeader.replace("Bearer ", "");
    }
}

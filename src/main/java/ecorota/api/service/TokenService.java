package ecorota.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import ecorota.api.repository.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiracao.minutos}")
    private Long minutes;

    public String gerar(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API EcoRota")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .withClaim("acess", usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .sign(algoritmo);
        } catch (JWTCreationException e) {
            throw new RuntimeException("erro ao gerar token jwt", e);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API EcoRota")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (TokenExpiredException exception) {
            throw new RuntimeException("Token JWT expirado!");
        } catch (JWTDecodeException exception) {
            throw new RuntimeException("Token JWT inválido !");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusMinutes(minutes).toInstant(ZoneOffset.of("-03:00"));
    }

}

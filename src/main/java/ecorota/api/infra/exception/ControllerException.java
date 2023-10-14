package ecorota.api.infra.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerException {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ObjetoException> tratarErro400(MethodArgumentNotValidException ex, HttpServletRequest req) {
        var obj = ObjetoExceptionFactory.create(ex, req);
        return ResponseEntity.badRequest().body(obj);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(403).body("Acesso negado Controller");
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity tratarErroBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity tratarErroAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tratarErroAuthentication21(DataIntegrityViolationException e) {
        System.err.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cadastro não realizado, email já existente!");
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> tokenExpirado(TokenExpiredException ex) {
        return ResponseEntity.status(403).body("Token JWT expirado!");
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<Object> tokenInvalido(JWTDecodeException ex) {
        return ResponseEntity.status(403).body("Token JWT inválido!");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> semTratamento(RuntimeException ex) {
        return ResponseEntity.status(500).body(ex.getMessage());
    }

}

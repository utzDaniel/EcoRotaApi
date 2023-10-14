package ecorota.api.infra.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public final class ObjetoExceptionFactory {

    private ObjetoExceptionFactory() {
    }

    public static ObjetoException create(MethodArgumentNotValidException ex, HttpServletRequest req) {
        var uri = req.getRequestURL().toString();
        var metodo = ex.getParameter().getMethod().getName();
        var title = String.format("%s inválido!", metodo);
        var status = 400;
        var detail = String.format("A requisição de %s não respeita os parametros!", metodo);
        List<ViolacaoErro> violacoes = ex.getFieldErrors().stream().map(ViolacaoErro::new).toList();
        return new ObjetoException(uri, title, status, detail, violacoes);
    }
}

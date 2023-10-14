package ecorota.api.infra.exception;

import org.springframework.validation.FieldError;

public record ViolacaoErro(String campo, String mensagem) {
    public ViolacaoErro(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }

}

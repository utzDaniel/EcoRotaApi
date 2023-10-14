package ecorota.api.infra.exception;

import java.util.List;

public record ObjetoException(String type, String title, int status, String detail, List<ViolacaoErro> violacoes) {

}

package ecorota.api.enun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpcaoTrajeto {

    MELHOR(1, "Melhor trajeto"),
    SUSTENTAVEL(2,"Mais sustentável"),
    ECONOMICO(3, "Mais econômico");

    private final int id;
    private final String nome;

    public static OpcaoTrajeto getOpcaoTrajeto(int id){
        for (OpcaoTrajeto opcao : OpcaoTrajeto.values()) {
            if (opcao.getId() == id) {
                return opcao;
            }
        }
        return null;
    }

}

package ecorota.api.enun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Transporte {

    ONIBUS(1, "Ônibus", 665, 7.12),
    METRO(2, "Metrô", 665, 1.1125),
    BICICLETA(3, "Bicicleta", 150, 0d),
    CAMINHADA(4, "Caminhada", 70, 0d);

    private final int id;
    private final String nome;
    private final double velocidadePorMinuto;
    private final double carbonoPorMinuto;
    public static final String UNIDADE_MEDIDA_CARBONO = "kg";

    public static Transporte getInstance(String nome) {
        return switch (nome) {
            case "Ônibus" -> Transporte.ONIBUS;
            case "Metrô" -> Transporte.METRO;
            case "Bicicleta" -> Transporte.BICICLETA;
            case "Caminhada" -> Transporte.CAMINHADA;
            default -> Transporte.CAMINHADA;
        };
    }

    public Long getIdTrajeto() {
        return this.getId() == 4 ? 1L : 2L;
    }

}

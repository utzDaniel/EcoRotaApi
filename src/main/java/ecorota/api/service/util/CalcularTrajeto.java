package ecorota.api.service.util;

import ecorota.api.enun.Transporte;

public final class CalcularTrajeto {
    private CalcularTrajeto() {
    }

    private static final double point_metro = 2.6;

    public static int distanciaEmMetros(int pointsSize) {
        return (int) (point_metro * pointsSize);
    }

    public static int tempoEmMinutos(Transporte transporte, int metros) {
        var tempo = (int) (metros / transporte.getVelocidadePorMinuto());
        return tempo == 0 ? 1 : tempo;
    }

    public static int emissaoCO2(Transporte transporte, int minutos) {
        return (int) (minutos * transporte.getCarbonoPorMinuto());
    }

}

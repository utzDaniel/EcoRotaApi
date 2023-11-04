package ecorota.api.service.util;

import ecorota.api.enun.Transporte;
import org.springframework.stereotype.Component;

@Component
public final class CalcularTrajeto {
    private CalcularTrajeto(){}

    public static double tempoEmMinutos(Transporte transporte, double km){
        return km / transporte.getVelocidadePorMinuto();
    }

    public static double emissaoCO2(Transporte transporte, double tempoEmMinutos){
        return tempoEmMinutos * transporte.getCarbonoPorMinuto();
    }

}

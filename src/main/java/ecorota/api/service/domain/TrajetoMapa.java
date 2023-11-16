package ecorota.api.service.domain;

public interface TrajetoMapa {

    Long getIdMapa();
    Long getIdTrajeto();
    boolean getPonto();
    int getTransporte();
    int getNumLinha();
    double getPassagem();
}

package ecorota.api.service.util;

import ecorota.api.enun.OpcaoTrajeto;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public final class OpcaoTrajetoConverter implements AttributeConverter<OpcaoTrajeto, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OpcaoTrajeto opcaoTrajeto) {
        return opcaoTrajeto.getId();
    }

    @Override
    public OpcaoTrajeto convertToEntityAttribute(Integer integer) {
        for (OpcaoTrajeto opcao : OpcaoTrajeto.values()) {
            if (opcao.getId() == integer) {
                return opcao;
            }
        }
        throw new IllegalArgumentException("Valor do enum OpcaoTrajeto desconhecido: " + integer);
    }
}

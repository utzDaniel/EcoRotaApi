package ecorota.api.service.util;

import ecorota.api.enun.Transporte;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public final class TransporteConverter implements AttributeConverter<Transporte, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Transporte transporte) {
        return transporte.getId();
    }

    @Override
    public Transporte convertToEntityAttribute(Integer integer) {
        for (Transporte transporte : Transporte.values()) {
            if (transporte.getId() == integer) {
                return transporte;
            }
        }
        throw new IllegalArgumentException("Valor do enum Transporte desconhecido: " + integer);
    }
}

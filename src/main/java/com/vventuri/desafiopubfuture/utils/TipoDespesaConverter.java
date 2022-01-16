package com.vventuri.desafiopubfuture.utils;

import com.vventuri.desafiopubfuture.entity.enums.TipoDespesa;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * The type Tipo despesa converter.
 */
@Component
    public class TipoDespesaConverter implements Converter<String, TipoDespesa> {

    @Override
    public TipoDespesa convert(String value) {
        return TipoDespesa.valueOf(value.toUpperCase());
    }
}
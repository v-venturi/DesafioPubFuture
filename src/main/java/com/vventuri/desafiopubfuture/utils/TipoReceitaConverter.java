package com.vventuri.desafiopubfuture.utils;

import com.vventuri.desafiopubfuture.entity.enums.TipoReceita;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * The type Tipo receita converter.
 */
@Component
public class TipoReceitaConverter implements Converter<String, TipoReceita> {

    @Override
    public TipoReceita convert(String value) {
        return TipoReceita.valueOf(value.toUpperCase());
    }
}
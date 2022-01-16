package com.vventuri.desafiopubfuture.utils;

import com.vventuri.desafiopubfuture.entity.enums.TipoConta;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * The type Tipo conta converter.
 */
@Component
public class TipoContaConverter implements Converter<String, TipoConta> {

    @Override
    public TipoConta convert(String value) {
        return TipoConta.valueOf(value.toUpperCase());
    }
}
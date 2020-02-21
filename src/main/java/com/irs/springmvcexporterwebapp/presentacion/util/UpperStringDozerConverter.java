package com.irs.springmvcexporterwebapp.presentacion.util;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

/**
 * Convertidor dozer de String a mayúsculas.
 *
 * @author IRS
 * @version 1.0.0
 */
public class UpperStringDozerConverter implements CustomConverter {

    /**
     * Constructor.
     */
    public UpperStringDozerConverter() {
        super();
    }

    public Object convert(Object destination, Object source,
            Class<?> destClass, Class<?> sourceClass) {
        if (source == null) {
            return null;
        }
        if (source instanceof java.lang.String) {
            return ((String) source).toUpperCase();
        } else {
            throw new MappingException("Converter UpperStringDozerConverter used incorrectly. Arguments passed in were:" + destination + " and " + source);
        }
    }
}

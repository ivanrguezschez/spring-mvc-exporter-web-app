package com.irs.springmvcexporterwebapp.presentacion.util;

import com.irs.springmvcexporterwebapp.negocio.vo.Identifiable;
import java.io.Serializable;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentificableDozerConverter implements CustomConverter {

    private static final Logger LOG = LoggerFactory.getLogger(IdentificableDozerConverter.class);

    private static final String CONVERT_MESSAGE
            = "Converter IdentificableDozerConverter used incorrectly. Arguments passed in were: %s and %s";

    @SuppressWarnings("unchecked")
    public Object convert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
        if (source == null) {
            return null;
        }
        Identifiable<Serializable> dest = null;
        try {
            if (source instanceof Serializable && !(source instanceof Identifiable<?>)) {
                if (destination == null) {
                    dest = (Identifiable<Serializable>) destClass.newInstance();
                } else {
                    dest = (Identifiable<Serializable>) destination;
                }
                dest.setId(((Serializable) source));

                return dest;
            } else if (source instanceof Identifiable<?>) {
                return ((Identifiable<Serializable>) source).getId();
            } else {
                throw new MappingException(String.format(CONVERT_MESSAGE, destination, source));
            }
        } catch (InstantiationException e) {
            LOG.warn(e.getMessage(), e);
            throw new MappingException(String.format(CONVERT_MESSAGE, destination, source));
        } catch (IllegalAccessException e) {
            LOG.warn(e.getMessage(), e);
            throw new MappingException(String.format(CONVERT_MESSAGE, destination, source));
        }
    }
}

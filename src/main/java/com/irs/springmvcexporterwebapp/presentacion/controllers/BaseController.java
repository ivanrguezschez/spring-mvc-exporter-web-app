package com.irs.springmvcexporterwebapp.presentacion.controllers;

import java.util.Locale;
import java.util.Properties;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * Clase abstracta base de los Controller's de la aplicación.
 *
 * @author IRS
 * @version 1.0.0
 */
public abstract class BaseController {

    @Autowired
    private Properties configProperties;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private DozerBeanMapper mapperPresentacion;

    /**
     * Constructor.
     */
    public BaseController() {
        super();
    }

    /**
     * Metodo que devuelve las propiedades de configuracion.
     *
     * @return Las propiedades de configuracion.
     */
    public Properties getConfigProperties() {
        return configProperties;
    }

    /**
     * Metodo que establece las propiedades de configuracion.
     *
     * @param configProperties Las propiedades de configuracion.
     */
    public void setConfigProperties(Properties configProperties) {
        this.configProperties = configProperties;
    }

    /**
     * Metodo que devuelve el objeto de mensajes i18n.
     *
     * @return El objeto de mensajes i18n.
     */
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * Metodo que establece el objeto de mensajes i18n.
     *
     * @param messageSource El objeto de mensajes i18n.
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Metodo que devuelve el objeto de dozer de mapeo.
     *
     * @return El objeto de dozer de mapeo.
     */
    public DozerBeanMapper getMapperPresentacion() {
        return mapperPresentacion;
    }

    /**
     * Metodo que establece el objeto de dozer de mapeo.
     *
     * @param mapperPresentacion El objeto de dozer de mapeo.
     */
    public void setMapperPresentacion(DozerBeanMapper mapperPresentacion) {
        this.mapperPresentacion = mapperPresentacion;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected Object toVo(Object command, Class voClass) {
        if (command != null) {
            return mapperPresentacion.map(command, voClass);
        }

        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected Object toCommand(Object vo, Class commandClass) {
        if (vo != null) {
            return mapperPresentacion.map(vo, commandClass);
        }

        return null;
    }

    protected String getMessage(String messageKey) {
        return messageSource.getMessage(messageKey, null, null);
    }

    protected String getMessage(String messageKey, Object[] messageArgs) {
        return messageSource.getMessage(messageKey, messageArgs, null);
    }

    protected String getMessage(String messageKey, Object[] messageArgs, Locale locale) {
        return messageSource.getMessage(messageKey, messageArgs, locale);
    }
}

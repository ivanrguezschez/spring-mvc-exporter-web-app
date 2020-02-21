package com.irs.springmvcexporterwebapp.negocio.servicios.exceptions;

/**
 * Clase de Excepcion Padre que controla las excepciones que se producen en 
 * los servicios de la capa de negocio.
 *
 * @author  IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class ServiceException extends Exception {
	
    /**
     * Construye una instancia de <code>ServiceException</code>.
     */
    public ServiceException() {
        super();
    }

    /**
     * Construye una instancia de <code>ServiceException</code> con el 
     * mensaje especificado del detalle del error.
     * @param message El mensaje del detalle del error.
     */
    public ServiceException(String message) {
        super(message);
    }
    
    /**
     * Construye una instancia de <code>ServiceException</code> con la causa del error.
     * @param cause La causa del error.
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
  
    /**
     * Construye una instancia de <code>ServiceException</code> con el mensaje 
     * especificado del detalle del error y la causa del mismo.
     * @param message El mensaje del detalle del error.
     * @param cause La causa del error.
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
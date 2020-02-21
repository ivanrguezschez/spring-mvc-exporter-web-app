package com.irs.springmvcexporterwebapp.persistencia.dao.exceptions;

/**
 * Clase de Excepcion Padre que controla las excepciones que se producen en los
 * dao's de la capa de persistencia.
 *
 * @author IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class DaoException extends Exception {

    /**
     * Crea una nueva instancia de <code>DaoException</code> vacia.
     */
    public DaoException() {
        super();
    }

    /**
     * Construye una instancia de <code>DaoException</code> con el mensaje
     * especificado del detalle del error.
     *
     * @param message El mensaje del detalle del error.
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Construye una instancia de <code>DaoException</code> con la causa del
     * error.
     *
     * @param cause La causa del error.
     */
    public DaoException(Throwable cause) {
        super(cause);
    }

    /**
     * Construye una instancia de <code>DaoException</code> con el mensaje
     * especificado del detalle del error y la causa del mismo.
     *
     * @param message El mensaje del detalle del error.
     * @param cause La causa del error.
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}

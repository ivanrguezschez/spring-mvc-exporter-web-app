package com.irs.springmvcexporterwebapp.dominio;

import java.io.Serializable;

/**
 * Interface para la identificacion de entidades del dominio.
 *
 * @author IRS
 * @version 1.0.0
 */
public interface Identifiable<PK extends Serializable> {

    /**
     * Método que devuelve el campo clave primaria.
     *
     * @return El campo clave primaria.
     */
    PK getPrimaryKey();

    /**
     * Método que estable el campo clave primaria.
     *
     * @param pk el campo clave primaria.
     */
    void setPrimaryKey(PK pk);

    /**
     * Método que devuelve el nombre del campo clave primaria.
     *
     * @return El nombre del campo clave primaria.
     */
    String getPrimaryKeyName();

    /**
     * Indica si la entidad identifiable es nueva (true) o no (false).
     *
     * @return true si la entidad identifiable es nueva o false si no lo es.
     */
    boolean isNew();
}

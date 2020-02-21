package com.irs.springmvcexporterwebapp.negocio.servicios.support;

import java.io.Serializable;

/**
 * Clase que almacena el criterio de ordenación. Este consiste en un tipo de
 * ordenación (asc|desc) y en nombre de la propiedad por la que se ordenará.
 *
 * @author IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class SortCriteria implements Serializable {

    /**
     * Tipo de ordenación.
     */
    private SortTypeEnum sortType; // asc | desc

    /**
     * Nombre de la propiedad por la que se ordenara.
     */
    private String sortProperty;

    /**
     * Constructor.
     *
     * @param sortProperty Nombre de la propiedad por la que se ordenará.
     */
    public SortCriteria(String sortProperty) {
        this(sortProperty, SortTypeEnum.ASC);
    }

    /**
     * Constructor.
     *
     * @param sortProperty Nombre de la propiedad por la que se ordenará.
     * @param sortType Tipo de ordenación.
     */
    public SortCriteria(String sortProperty, SortTypeEnum sortType) {
        super();
        this.sortType = sortType;
        this.sortProperty = sortProperty;
    }

    /**
     * Método que devuelve el tipo de ordenación.
     *
     * @return El tipo de ordenación.
     */
    public SortTypeEnum getSortType() {
        return sortType;
    }

    /**
     * Método que establece el tipo de ordenación.
     *
     * @param sortType El tipo de ordenación.
     */
    public void setSortType(SortTypeEnum sortType) {
        this.sortType = sortType;
    }

    /**
     * Método que devuelve la propiedad de ordenación.
     *
     * @return La propiedad de ordenación.
     */
    public String getSortProperty() {
        return sortProperty;
    }

    /**
     * Método que establece la propiedad de ordenación.
     *
     * @param sortProperty La propiedad de ordenación.
     */
    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    /**
     * Método que indica si el criterio de ordenación es null o no.
     *
     * @return true si es null, false en caso contrario.
     */
    public boolean isNull() {
        return (this.getSortType() == null
                && (this.getSortProperty() == null || this.getSortProperty().trim().length() == 0));
    }

    /**
     * Métido que indica si el criterio de ordenación es válido. Comprueba si la
     * propiedad indicada existe en la clase de objeto especificado.
     *
     * @param classType Tipo de objecto en el que debe existir la propiedad.
     * @return true si es válido, false en caso contrario.
     */
    @SuppressWarnings("rawtypes")
    public boolean isValid(Class classType) {
        return this.getSortType() != null && SearchUtil.checkSortProperty(classType, this.getSortProperty());
    }

    /**
     * Métido que indica si el criterio de ordenación es no válido. Comprueba si
     * la propiedad indicada no existe en la clase de objeto especificado.
     *
     * @param classType Tipo de objecto en el que debe existir la propiedad.
     * @return true si es no válido, false en caso contrario.
     */
    @SuppressWarnings("rawtypes")
    public boolean isNotValid(Class classType) {
        return !isValid(classType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getCanonicalName());
        sb.append(" (");
        sb.append("sortType=").append(sortType).append(", ");
        sb.append("sortProperty=").append(sortProperty);
        sb.append(")");

        return sb.toString();
    }
}

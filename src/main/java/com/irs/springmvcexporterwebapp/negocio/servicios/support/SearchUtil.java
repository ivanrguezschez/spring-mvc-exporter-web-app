package com.irs.springmvcexporterwebapp.negocio.servicios.support;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase de utilidad para emplear en los criterios de ordenación..
 *
 * @author IRS
 * @version 1.0.0
 */
public class SearchUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SearchUtil.class);

    /**
     * Ordenación ascendente.
     */
    public static final String SORT_ASC = "asc";

    /**
     * Ordenación descendente.
     */
    public static final String SORT_DESC = "desc";

    private SearchUtil() {
    }

    /**
     * Método que convierte un String en el tipo enumerado SortType.
     *
     * @param value Valor a convertir.
     * @return El tipo enumerado SortType.
     */
    public static SortTypeEnum stringToEnum(String value) {
        if (value == null) {
            return null;
        }

        return SortTypeEnum.valueOf(value.toUpperCase());
    }

    /**
     * Método que comprueba si el nombre del campo existe en el tipo de clase
     * especificado.
     *
     * @param classType Tipo de clase en la que se comprobará la existencia de
     * la propiedad.
     * @param fieldName Nombre de la propiedad.
     * @return true si la propiedad existe en el tipo de clase, false en caso
     * contrario.
     */
    @SuppressWarnings("rawtypes")
    public static boolean checkSortProperty(Class classType, String fieldName) {
        boolean result = true;

        try {
            classType.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            LOG.warn("El campo con nombre '" + fieldName + "' no existe", e);
            result = false;
        } catch (SecurityException e) {
            LOG.warn("No se tiene acceso al campo con nombre '" + fieldName + "'", e);
            result = false;
        }

        return result;
    }

    /**
     * Método que añade un criterio de ordenación a un objeto DetachedCriteria
     * de Hibernate.
     *
     * @param criteria Objeto DetachedCriteria de Hibernate en el que se añadira
     * el criterio.
     * @param sortDirection Dirección de ordenación.
     * @param sortCriterion Propiedad por la que se ordenará.
     */
    public static void addOrder(DetachedCriteria criteria,
            String sortDirection, String sortCriterion) {
        if (SORT_DESC.equalsIgnoreCase(sortDirection)) {
            criteria.addOrder(Order.desc(sortCriterion));
        } else {
            // Por defecto establezco el tipo de ordenacion a asc
            criteria.addOrder(Order.asc(sortCriterion));
        }
    }

    /**
     * Método que añade un criterio de ordenación a un objeto DetachedCriteria
     * de Hibernate.
     *
     * @param criteria Objeto DetachedCriteria de Hibernate en el que se añadira
     * el criterio.
     * @param sortCriteria Criterio de ordenación a añadir.
     */
    public static void addOrder(DetachedCriteria criteria,
            SortCriteria sortCriteria) {
        addOrder(criteria, sortCriteria.getSortType(),
                sortCriteria.getSortProperty());
    }

    /**
     * Método que añade un criterio de ordenación a un objeto DetachedCriteria
     * de Hibernate.
     *
     * @param criteria Objeto DetachedCriteria de Hibernate en el que se añadira
     * el criterio.
     * @param sortType Tipo de ordenación.
     * @param sortProperty Propiedad de ordenación.
     */
    public static void addOrder(DetachedCriteria criteria,
            SortTypeEnum sortType, String sortProperty) {
        if (SortTypeEnum.DESC == sortType) {
            criteria.addOrder(Order.desc(sortProperty));
        } else {
            // Por defecto establezco el tipo de ordenacion a asc
            criteria.addOrder(Order.asc(sortProperty));
        }
    }

    /**
     * Método que añade un criterio de ordenación a un objeto Criteria de
     * Hibernate.
     *
     * @param criteria Objeto Criteria de Hibernate en el que se añadira el
     * criterio.
     * @param sortDirection Dirección de ordenación.
     * @param sortCriterion Propiedad por la que se ordenará.
     */
    public static void addOrder(Criteria criteria,
            String sortDirection, String sortCriterion) {
        if (SORT_DESC.equalsIgnoreCase(sortDirection)) {
            criteria.addOrder(Order.desc(sortCriterion));
        } else {
            // Por defecto establezco el tipo de ordenacion a asc
            criteria.addOrder(Order.asc(sortCriterion));
        }
    }

    /**
     * Método que añade un criterio de ordenación a un objeto Criteria de
     * Hibernate.
     *
     * @param criteria Objeto Criteria de Hibernate en el que se añadira el
     * criterio.
     * @param sortCriteria Criterio de ordenación a añadir.
     */
    public static void addOrder(Criteria criteria,
            SortCriteria sortCriteria) {
        addOrder(criteria, sortCriteria.getSortType(),
                sortCriteria.getSortProperty());
    }

    /**
     * Método que añade un criterio de ordenación a un objeto Criteria de
     * Hibernate.
     *
     * @param criteria Objeto Criteria de Hibernate en el que se añadira el
     * criterio.
     * @param sortType Tipo de ordenación.
     * @param sortProperty Propiedad de ordenación.
     */
    public static void addOrder(Criteria criteria,
            SortTypeEnum sortType, String sortProperty) {
        if (SortTypeEnum.DESC == sortType) {
            criteria.addOrder(Order.desc(sortProperty));
        } else {
            // Por defecto establezco el tipo de ordenacion a asc
            criteria.addOrder(Order.asc(sortProperty));
        }
    }
}

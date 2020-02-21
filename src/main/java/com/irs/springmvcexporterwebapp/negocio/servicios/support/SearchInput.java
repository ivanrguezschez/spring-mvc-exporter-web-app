package com.irs.springmvcexporterwebapp.negocio.servicios.support;

import java.io.Serializable;

/**
 * Clase que almacena los datos de entrada de la busqueda.
 *
 * @author IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class SearchInput<T extends Serializable> implements Serializable {

    private SearchConfig searchConfig;

    private int pageNumber;

    private T parameter;

    private SortCriteria sortCriteria;

    public SearchInput() {
        this(null);
    }

    public SearchInput(T parameter) {
        this(new SearchConfig(), SearchConfig.PAGE_NUMBER_DEFAULT, parameter, null);
    }

    public SearchInput(SearchConfig searchTemplate, T parameter) {
        this(searchTemplate, SearchConfig.PAGE_NUMBER_DEFAULT, parameter, null);
    }

    public SearchInput(SearchConfig searchConfig, int pageNumber, T parameter) {
        this(searchConfig, pageNumber, parameter, null);
    }

    public SearchInput(SearchConfig searchConfig, int pageNumber, T parameter, SortCriteria sortCriteria) {
        super();
        this.searchConfig = searchConfig;
        this.pageNumber = pageNumber;
        this.parameter = parameter;
        this.sortCriteria = sortCriteria;
    }

    public SearchConfig getSearchConfig() {
        return searchConfig;
    }

    public void setSearchConfig(SearchConfig searchConfig) {
        this.searchConfig = searchConfig;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber <= 0) {
            pageNumber = SearchConfig.PAGE_NUMBER_DEFAULT;
        }

        this.pageNumber = pageNumber;
    }

    public T getParameter() {
        return this.parameter;
    }

    public void setParameter(T parameter) {
        this.parameter = parameter;
    }

    public int getFirstResult() {
        return (getPageNumber() - 1) * searchConfig.getPageSize();
    }

    public SortCriteria getSortCriteria() {
        return sortCriteria;
    }

    public void setSortCriteria(SortCriteria sortCriteria) {
        this.sortCriteria = sortCriteria;
    }
}

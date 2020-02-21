package com.irs.springmvcexporterwebapp.negocio.servicios.support;

import java.io.Serializable;

/**
 * Clase que almacena los datos de salida de la busqueda.
 *
 * @author IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class SearchOutput<T extends Serializable> implements Serializable {

    private SearchConfig searchConfig;

    private int pageNumber;

    private int total;

    private SearchResult searchResult;

    private SortCriteria sortCriteria;

    public SearchOutput() {
        this(new SearchConfig(), SearchConfig.PAGE_NUMBER_DEFAULT, null);
    }

    public SearchOutput(SearchConfig searchConfig, int pageNumber) {
        this(searchConfig, pageNumber, null);
    }

    public SearchOutput(SearchConfig searchConfig, int pageNumber, SortCriteria sortCriteria) {
        super();
        this.searchConfig = searchConfig;
        this.pageNumber = pageNumber;
        this.total = 0;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public int getTotalPages() {
        return ((total - 1) / searchConfig.getPageSize()) + 1;
    }

    public int getFirstLinkedPage() {
        return Math.max(1, pageNumber - (searchConfig.getMaxLinkedPages() / 2));
    }

    public int getLastLinkedPage() {
        return Math.min(getFirstLinkedPage() + searchConfig.getMaxLinkedPages() - 1, getTotalPages());
    }

    public boolean isHasFirstPage() {
        return (getPageNumber() > 1);
    }

    public boolean isHasPreviousPage() {
        return (getPageNumber() > 1);
    }

    public boolean isHasLastPage() {
        return (getPageNumber() < getTotalPages());
    }

    public boolean isHasNextPage() {
        return (getPageNumber() < getTotalPages());
    }

    public int getPreviousPage() {
        return getPageNumber() - 1;
    }

    public int getNextPage() {
        return getPageNumber() + 1;
    }

    /**
     * Método que obtiene el criterio de ordenación.
     *
     * @return El criterio de ordenación.
     */
    public SortCriteria getSortCriteria() {
        return sortCriteria;
    }

    /**
     * Método que establece el criterio de ordenación.
     *
     * @param sortCriteria Criterio de ordenación a establecer.
     */
    public void setSortCriteria(SortCriteria sortCriteria) {
        this.sortCriteria = sortCriteria;
    }
}

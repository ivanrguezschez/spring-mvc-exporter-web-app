package com.irs.springmvcexporterwebapp.negocio.servicios.support;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de resultado de la busqueda como lista.
 *
 * @author IRS
 * @version 1.0.0
 */
public class ListSearchResult<T> implements SearchResult {

    private List<T> result;

    public ListSearchResult() {
        super();
        this.result = null;
    }

    public ListSearchResult(List<T> newResult) {
        super();
        if (newResult == null) {
            this.result = null;
        } else {
            this.result = new ArrayList<T>(newResult);
        }
    }

    public List<T> getResult() {
        return this.result;
    }
}

package com.irs.springmvcexporterwebapp.negocio.servicios.support;

import java.io.Serializable;

/**
 * Clase que almacenal los parametros de la paginación.
 *
 * @author IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class SearchConfig implements Serializable {
    
    public static final int PAGE_NUMBER_DEFAULT = 1;
    
    public static final int PAGE_SIZE_DEFAULT = 10;

    public static final int MAX_LINKED_PAGES_DEAFULT = 10;
    
    // Tamaño de la página (Número de registros a mostrar por página)
    private int pageSize;

    // Número maximo de enlaces de pagina a mostrar
    private int maxLinkedPages;

    public SearchConfig() {
        super();
        this.pageSize = SearchConfig.PAGE_SIZE_DEFAULT;
        this.maxLinkedPages = SearchConfig.MAX_LINKED_PAGES_DEAFULT; 
    }
    
    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            pageSize = SearchConfig.PAGE_SIZE_DEFAULT;
        }

        this.pageSize = pageSize;
    }

    public int getMaxLinkedPages() {
        return this.maxLinkedPages;
    }

    public void setMaxLinkedPages(int maxLinkedPages) {
        if (pageSize <= 0) {
            maxLinkedPages = SearchConfig.MAX_LINKED_PAGES_DEAFULT;
        }

        this.maxLinkedPages = maxLinkedPages;
    }
}

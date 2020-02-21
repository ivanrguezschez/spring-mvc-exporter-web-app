package com.irs.springmvcexporterwebapp.negocio.servicios;

import com.irs.springmvcexporterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchInput;
import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchOutput;
import com.irs.springmvcexporterwebapp.negocio.vo.FilterUsuarioVO;
import com.irs.springmvcexporterwebapp.negocio.vo.UsuarioVO;

/**
 * Interface de la lógica de negocio empleada para los usuarios.
 *
 * @author IRS
 * @version 1.0.0
 */
public interface UsuarioService {

    /**
     * Busca usuarios por filtro.
     *
     * @param searchInput Filtro empleado en la busqueda.
     * @return Una lista con los usuarios que cumplan el filtro de busqueda.
     * @throws ServiceException Si se produce un error en la busqueda.
     */
    SearchOutput<UsuarioVO> findByFilter(SearchInput<FilterUsuarioVO> searchInput) throws ServiceException;
}

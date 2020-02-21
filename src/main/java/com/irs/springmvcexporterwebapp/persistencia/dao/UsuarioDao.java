package com.irs.springmvcexporterwebapp.persistencia.dao;

import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchInput;
import com.irs.springmvcexporterwebapp.negocio.vo.FilterUsuarioVO;
import com.irs.springmvcexporterwebapp.negocio.vo.UsuarioVO;
import com.irs.springmvcexporterwebapp.persistencia.dao.exceptions.DaoException;
import java.util.List;

/**
 * Interface de la capa de persistencia empleada para los usuarios.
 *
 * @author IRS
 * @version 1.0.0
 */
public interface UsuarioDao {

    /**
     * Selecciona una lista de usuarios por filtro de busqueda.
     *
     * @param searchInput Filtro de busqueda empleado.
     * @return Una lista con los usuarios que cumplen el filtro de busqueda.
     * @throws DaoException Si se produce algún error en la selección.
     */
    List<UsuarioVO> selectByFilter(
            final SearchInput<FilterUsuarioVO> searchInput) throws DaoException;

    /**
     * Cuenta el número de usuarios por filtro de busqueda.
     *
     * @param searchInput Filtro de busqueda empleado.
     * @return El número de usuarios que cumplen el filtro de busqueda.
     * @throws DaoException Si se produce algún error en la selección.
     */
    int countByFilter(final SearchInput<FilterUsuarioVO> searchInput)
            throws DaoException;
}

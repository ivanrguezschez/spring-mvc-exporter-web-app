package com.irs.springmvcexporterwebapp.negocio.servicios.impl;

import com.irs.springmvcexporterwebapp.negocio.servicios.UsuarioService;
import com.irs.springmvcexporterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springmvcexporterwebapp.negocio.servicios.support.ListSearchResult;
import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchInput;
import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchOutput;
import com.irs.springmvcexporterwebapp.negocio.vo.FilterUsuarioVO;
import com.irs.springmvcexporterwebapp.negocio.vo.UsuarioVO;
import com.irs.springmvcexporterwebapp.persistencia.dao.UsuarioDao;
import com.irs.springmvcexporterwebapp.persistencia.dao.exceptions.DaoException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementanción de la interface de la logica de negocio empleada para los
 * usuarios.
 *
 * @author IRS
 * @version 1.0.0
 */
@Service("com.irs.springbootstrapvalidationwebapp.negocio.servicios.UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    /**
     * Objeto de persistencia de usuario.
     */
    @Autowired
    private UsuarioDao usuarioDao;

    /**
     * Constructor por defecto.
     */
    public UsuarioServiceImpl() {
        super();
    }

    /**
     * Busca usuarios por filtro.
     *
     * @param searchInput Filtro empleado en la busqueda.
     * @return Una lista con los usuarios que cumplan el filtro de busqueda.
     * @throws ServiceException Si se produce un error en la busqueda.
     */
    public SearchOutput<UsuarioVO> findByFilter(
            SearchInput<FilterUsuarioVO> searchInput) throws ServiceException {
        SearchOutput<UsuarioVO> searchOutput = new SearchOutput<UsuarioVO>(
                searchInput.getSearchConfig(), searchInput.getPageNumber());

        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Buscando usuarios por filtro '{}'", searchInput.getParameter());
            }
            List<UsuarioVO> items = usuarioDao.selectByFilter(searchInput);
            searchOutput.setSearchResult(new ListSearchResult<UsuarioVO>(items));
            searchOutput.setTotal(usuarioDao.countByFilter(searchInput));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return searchOutput;
    }
}

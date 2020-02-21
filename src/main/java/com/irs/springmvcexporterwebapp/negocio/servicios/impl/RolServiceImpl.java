package com.irs.springmvcexporterwebapp.negocio.servicios.impl;

import com.irs.springmvcexporterwebapp.negocio.servicios.RolService;
import com.irs.springmvcexporterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springmvcexporterwebapp.negocio.vo.RolVO;
import com.irs.springmvcexporterwebapp.persistencia.dao.RolDao;
import com.irs.springmvcexporterwebapp.persistencia.dao.exceptions.DaoException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementanción de la interface de la logica de negocio empleada para los
 * roles.
 *
 * @author IRS
 * @version 1.0.0
 */
@Service("com.irs.springbootstrapvalidationwebapp.negocio.servicios.RolService")
public class RolServiceImpl implements RolService {

    private static final Logger LOG = LoggerFactory.getLogger(RolServiceImpl.class);

    /**
     * Objeto de persistencia de rol.
     */
    @Autowired
    private RolDao rolDao;

    /**
     * Constructor por defecto.
     */
    public RolServiceImpl() {
        super();
    }

    public List<RolVO> findAll() throws ServiceException {
         List<RolVO> result = null;

        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Buscando tolos los roles");
            }
            result = rolDao.selectAll();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(e);
        }

        return result;
    }
}

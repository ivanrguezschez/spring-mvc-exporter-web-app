package com.irs.springmvcexporterwebapp.negocio.servicios;

import com.irs.springmvcexporterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springmvcexporterwebapp.negocio.vo.RolVO;
import java.util.List;

/**
 * Interface de la lógica de negocio empleada para los roles.
 *
 * @author IRS
 * @version 1.0.0
 */
public interface RolService {

    List<RolVO> findAll() throws ServiceException;
}

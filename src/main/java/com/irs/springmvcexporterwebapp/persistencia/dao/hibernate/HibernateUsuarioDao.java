package com.irs.springmvcexporterwebapp.persistencia.dao.hibernate;

import com.irs.springmvcexporterwebapp.dominio.Usuario;
import com.irs.springmvcexporterwebapp.negocio.servicios.support.SearchInput;
import com.irs.springmvcexporterwebapp.negocio.vo.FilterUsuarioVO;
import com.irs.springmvcexporterwebapp.negocio.vo.UsuarioVO;
import com.irs.springmvcexporterwebapp.persistencia.dao.UsuarioDao;
import com.irs.springmvcexporterwebapp.persistencia.dao.exceptions.DaoException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;

/**
 * Implementacion basada en Hibernate de la interface de la capa de persistencia
 * empleada para los usuarios.
 *
 * @author IRS
 * @version 1.0.0
 */
@Repository
public class HibernateUsuarioDao implements UsuarioDao {

    private static final Logger LOG = LoggerFactory.getLogger(HibernateUsuarioDao.class);

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected DozerBeanMapper mapperPersistencia;

    /**
     * Constructor por defecto.
     */
    public HibernateUsuarioDao() {
        super();
    }

    /**
     * Selecciona usuarios por filtro.
     *
     * @param searchInput Filtro de busqueda empleado.
     * @return Una lista con los usuarios que cumplen el filtro de busqueda.
     * @throws DaoException Si se produce algún error en la selección.
     */
    @SuppressWarnings("unchecked")
    public List<UsuarioVO> selectByFilter(SearchInput<FilterUsuarioVO> searchInput)
            throws DaoException {
        List<UsuarioVO> result = null;
        FilterUsuarioVO filter = searchInput.getParameter();

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Seleccionando usuarios por filtro: {}", filter);
            }

            Criteria criteria = buildCriteria(filter);
            
            if (searchInput.getPageNumber() >= 1) {
                criteria.setFirstResult(searchInput.getFirstResult());
                criteria.setMaxResults(searchInput.getSearchConfig().getPageSize());
            }
            
            result = toVo((List<Usuario>) criteria.list());

            if (LOG.isDebugEnabled()) {
                LOG.debug("Numero de usuarios encontrados: {}", (result == null ? 0 : result.size()));
            }
        } catch (Exception e) {
            throw new DaoException("Error seleccionando usuarios por filtro '" + filter + "': " + e.getMessage(), e);
        }

        return result;
    }

    /**
     * Cuenta el número de usuarios por filtro de busqueda.
     *
     * @param searchInput Filtro de busqueda empleado.
     * @return El número de usuarios que cumplen el filtro de busqueda.
     * @throws DaoException Si se produce algún error en la selección.
     */
    public int countByFilter(SearchInput<FilterUsuarioVO> searchInput)
            throws DaoException {
        int result = 0;
        FilterUsuarioVO filter = searchInput.getParameter();

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Contando usuarios por filtro: {}", filter);
            }

            Criteria criteria = buildCriteria(filter);
            
            criteria.setProjection(Projections.rowCount());
            List list = criteria.list();
            if (list != null && !list.isEmpty()) {
                result = DataAccessUtils.intResult(list);
            }
            
            if (LOG.isDebugEnabled()) {
                LOG.debug("Numero de usuarios: {}", result);
            }
        } catch (Exception e) {
            throw new DaoException("Error contando usuarios por filtro '" + filter + "': " + e.getMessage(), e);
        }

        return result;
    }

    private Criteria buildCriteria(FilterUsuarioVO filterUsuario) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuario.class);

        if (filterUsuario != null) {
            if (StringUtils.isNotEmpty(filterUsuario.getNif())) {
                criteria.add(Restrictions.ilike("nif", filterUsuario.getNif(), MatchMode.ANYWHERE));
            }
            if (StringUtils.isNotEmpty(filterUsuario.getNombre())) {
                criteria.add(Restrictions.ilike("nombre", filterUsuario.getNombre(), MatchMode.ANYWHERE));
            }
            if (StringUtils.isNotEmpty(filterUsuario.getApellido1())) {
                criteria.add(Restrictions.ilike("apellido1", filterUsuario.getApellido1(), MatchMode.ANYWHERE));
            }
            if (StringUtils.isNotEmpty(filterUsuario.getApellido2())) {
                criteria.add(Restrictions.ilike("apellido2", filterUsuario.getApellido2(), MatchMode.ANYWHERE));
            }
            if (filterUsuario.getRol() != null) {
                criteria.add(Restrictions.eq("rol.idRol", filterUsuario.getRol().getIdRol()));
            }

            // Comentado ya que no funciona con HSQL
            //criteria.addOrder(Order.asc(Const.FN_NIF));
            //criteria.addOrder(Order.asc(Const.FN_NOMBRE));
            //criteria.addOrder(Order.asc(Const.FN_APELLIDO1));
            //criteria.addOrder(Order.asc(Const.FN_APELLIDO2));
        }

        return criteria;
    }

    private UsuarioVO toVo(Usuario entity) {
        if (entity != null) {
            return (UsuarioVO) mapperPersistencia.map(entity, UsuarioVO.class);
        }

        return null;
    }

    private List<UsuarioVO> toVo(List<Usuario> entities) {
        List<UsuarioVO> vos = null;

        if (entities != null) {
            vos = new ArrayList<UsuarioVO>();
            for (Usuario entity : entities) {
                vos.add(toVo(entity));
            }
        }

        return vos;
    }

    private Usuario toEntity(UsuarioVO vo) {
        if (vo != null) {
            return (Usuario) mapperPersistencia.map(vo, Usuario.class);
        }

        return null;
    }

    private List<Usuario> toEntity(List<UsuarioVO> vos) {
        List<Usuario> entities = null;

        if (vos != null) {
            entities = new ArrayList<Usuario>();
            for (UsuarioVO vo : vos) {
                entities.add(toEntity(vo));
            }
        }

        return entities;
    }
}

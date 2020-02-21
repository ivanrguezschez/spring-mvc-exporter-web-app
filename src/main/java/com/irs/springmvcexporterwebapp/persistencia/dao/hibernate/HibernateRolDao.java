package com.irs.springmvcexporterwebapp.persistencia.dao.hibernate;

import com.irs.springmvcexporterwebapp.dominio.Rol;
import com.irs.springmvcexporterwebapp.negocio.vo.RolVO;
import com.irs.springmvcexporterwebapp.persistencia.dao.RolDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementacion basada en Hibernate de la interface de la capa de persistencia
 * empleada para los roles.
 *
 * @author IRS
 * @version 1.0.0
 */
@Repository
public class HibernateRolDao implements RolDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected DozerBeanMapper mapperPersistencia;

    /**
     * Constructor por defecto.
     */
    public HibernateRolDao() {
        super();
    }

    public List<RolVO> selectAll() {
        List<RolVO> result = null;

        result = toVo((List<Rol>) sessionFactory.getCurrentSession().createQuery("from Rol order by rol").list());

        return result;
    }

    private RolVO toVo(Rol entity) {
        if (entity != null) {
            return (RolVO) mapperPersistencia.map(entity, RolVO.class);
        }

        return null;
    }

    private List<RolVO> toVo(List<Rol> entities) {
        List<RolVO> vos = null;

        if (entities != null) {
            vos = new ArrayList<RolVO>();
            for (Rol entity : entities) {
                vos.add(toVo(entity));
            }
        }

        return vos;
    }

    private Rol toEntity(RolVO vo) {
        if (vo != null) {
            return (Rol) mapperPersistencia.map(vo, Rol.class);
        }

        return null;
    }

    private List<Rol> toEntity(List<RolVO> vos) {
        List<Rol> entities = null;

        if (vos != null) {
            entities = new ArrayList<Rol>();
            for (RolVO vo : vos) {
                entities.add(toEntity(vo));
            }
        }

        return entities;
    }
}

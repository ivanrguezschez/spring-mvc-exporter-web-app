package com.irs.springmvcexporterwebapp.negocio.vo;

/**
 * Clase de negocio Value Object (VO) empleado para almacenar los roles.
 *
 * @author IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class RolVO extends BaseVO implements Identifiable<Integer> {

    /**
     * Identificador del rol.
     */
    private Integer idRol;

    /**
     * Nombre del rol.
     */
    private String rol;

    /**
     * Constructor por defecto.
     */
    public RolVO() {
        this(null, null);
    }

    /**
     * Constructor.
     *
     * @param idRol Identificador del rol.
     * @param rol Nombre del rol.
     */
    public RolVO(Integer idRol, String rol) {
        super();
        this.idRol = idRol;
        this.rol = rol;
    }

    /**
     * Método que obtiene el identificador del rol.
     *
     * @return Devuelve el identificador del rol.
     */
    public Integer getIdRol() {
        return idRol;
    }

    /**
     * Método que establece el identificador del rol.
     *
     * @param idRol El identificador del rol.
     */
    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    /**
     * Método que obtiene nombres del rol.
     *
     * @return Devuelve el nombre del rol.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Método que establece el nombre del rol.
     *
     * @param rol El nombre del rol.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Método que obtiene el id del rol.
     *
     * @return Devuelve el id del rol.
     */
    public Integer getId() {
        return getIdRol();
    }

    /**
     * Método que establece el id del rol.
     *
     * @param id El id del rol.
     */
    public void setId(Integer id) {
        setIdRol(id);
    }

    /**
     * Método que obtiene el nombre del id del rol.
     *
     * @return Devuelve el nombre del id del rol.
     */
    public String getIdName() {
        return "idRol";
    }

    /**
     * Método que indica si del rol es nuevo (true) o no (false).
     *
     * @return Devuelve true si del rol es nuevo, false en caso contrario.
     */
    public boolean isNew() {
        return (getIdRol() == null) || (getIdRol().intValue() == 0);
    }
}

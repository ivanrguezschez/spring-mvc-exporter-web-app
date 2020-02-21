package com.irs.springmvcexporterwebapp.negocio.vo;

import java.util.Date;

/**
 * Clase de negocio Value Object (VO) empleado para almacenar los usuarios.
 *
 * @author IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class UsuarioVO extends BaseVO implements Identifiable<Integer> {

    /**
     * Identificador del usuario.
     */
    private Integer idUsuario;

    /**
     * Nif del usuario.
     */
    private String nif;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Primer apellido del usuario.
     */
    private String apellido1;

    /**
     * Segundo apellido del usuario.
     */
    private String apellido2;

    /**
     * Email del usuario.
     */
    private String email;

    /**
     * Fecha de alta del usuario.
     */
    private Date fechaAlta;

    /**
     * Rol del usuario.
     */
    private RolVO rol;

    /**
     * Constructor por defecto.
     */
    public UsuarioVO() {
        super();
        this.idUsuario = null;
        this.nif = null;
        this.nombre = null;
        this.apellido1 = null;
        this.apellido2 = null;
        this.email = null;
        this.fechaAlta = null;
        this.rol = null;
    }

    /**
     * Método que obtiene el identificador del usuario.
     *
     * @return Devuelve el identificador del usuario.
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Método que establece el identificador del usuario.
     *
     * @param idUsuario El identificador del usuario.
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Método que obtiene el nif del usuario.
     *
     * @return Devuelve el nif del usuario.
     */
    public String getNif() {
        return nif;
    }

    /**
     * Método que establece el nif del usuario.
     *
     * @param nif El nif del usuario.
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Método que obtiene el nombre del usuario.
     *
     * @return Devuelve el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre del usuario.
     *
     * @param nombre El nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que obtiene el primer apellido del usuario.
     *
     * @return Devuelve el primer apellido del usuario.
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Método que establece el primer apellido del usuario.
     *
     * @param apellido1 El primer apellido del usuario.
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Método que obtiene el segundo apellido del usuario.
     *
     * @return Devuelve el segundo apellido del usuario.
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Método que establece el segundo apellido del usuario.
     *
     * @param apellido2 El segundo apellido del usuario.
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * Método que obtiene el email del usuario.
     *
     * @return Devuelve el email del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que establece el email del usuario.
     *
     * @param email El email del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método que obtiene la fecha de alta del usuario.
     *
     * @return Devuelve la fecha de alta del usuario.
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Método que establece la fecha de alta del usuario.
     *
     * @param fechaAlta La fecha de alta del usuario.
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Método que obtiene el rol del usuario.
     *
     * @return Devuelve el rol del usuario.
     */
    public RolVO getRol() {
        return rol;
    }

    /**
     * Método que establece el rol del usuario.
     *
     * @param rol El rol del usuario.
     */
    public void setRol(RolVO rol) {
        this.rol = rol;
    }

    /**
     * Método que obtiene el id del usuario.
     *
     * @return Devuelve el id del usuario.
     */
    public Integer getId() {
        return getIdUsuario();
    }

    /**
     * Método que establece el id del usuario.
     *
     * @param id El id del usuario.
     */
    public void setId(Integer id) {
        setIdUsuario(id);
    }

    /**
     * Método que obtiene el nombre del id del usuario.
     *
     * @return Devuelve el nombre del id del usuario.
     */
    public String getIdName() {
        return "idUsuario";
    }

    /**
     * Método que indica si del usuario es nuevo (true) o no (false).
     *
     * @return Devuelve true si del usuario es nuevo, false en caso contrario.
     */
    public boolean isNew() {
        return (getIdUsuario() == null) || (getIdUsuario().intValue() == 0);
    }
}

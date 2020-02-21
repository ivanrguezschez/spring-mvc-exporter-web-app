package com.irs.springmvcexporterwebapp.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Clase del dominio empleada para almacenar los roles.
 *
 * @author IRS
 * @version 1.0.0
 */
@Entity
@Table(name = "ROL", uniqueConstraints = @UniqueConstraint(columnNames = "ROL"))
public class Rol extends BaseEntity implements Identifiable<Integer> {

    /**
     * Identificador del rol.
     */
    @Id
    @Column(name = "ID_ROL", unique = true, nullable = false, precision = 8, scale = 0)
    private Integer idRol;

    /**
     * Nombre del rol.
     */
    @Column(name = "ROL", unique = true, nullable = false, length = 50)
    private String rol;

    /**
     * Constructor por defecto.
     */
    public Rol() {
        super();
        this.idRol = null;
        this.rol = null;
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
     * Método que obtiene el nombre del rol.
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
     * Método que obtiene la clave primaria del rol.
     *
     * @return Devuelve la clave primaria del rol.
     */
    @Transient
    public Integer getPrimaryKey() {
        return getIdRol();
    }

    /**
     * MÃ©todo que establece la clave primaria del rol.
     *
     * @param pk La clave primaria del rol.
     */
    @Transient
    public void setPrimaryKey(Integer pk) {
        setIdRol(pk);
    }

    /**
     * MÃ©todo que obtiene el nombre de la clave primaria del rol.
     *
     * @return Devuelve el nombre de la clave primaria del rol.
     */
    @Transient
    public String getPrimaryKeyName() {
        return "idRol";
    }

    /**
     * MÃ©todo que indica si el rol es nuevo (true) o no (false).
     *
     * @return Devuelve true si el rol es nuevo, false en caso contrario.
     */
    @Transient
    public boolean isNew() {
        return getIdRol() == null;
    }
}

package com.irs.springmvcexporterwebapp.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Clase del dominio empleada para almacenar los usuarios.
 *
 * @author IRS
 * @version 1.0.0
 */
@Entity
@Table(name = "USUARIO", uniqueConstraints = @UniqueConstraint(columnNames = "NIF"))
@SuppressWarnings("serial")
public class Usuario extends BaseEntity implements Identifiable<Integer> {

    /**
     * Identificador del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO", unique = true, nullable = false, precision = 8, scale = 0)
    private Integer idUsuario;

    /**
     * Nif del usuario.
     */
    @Column(name = "NIF", unique = true, nullable = false, length = 9)
    private String nif;

    /**
     * Nombre del usuario.
     */
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    /**
     * Primer apellido del usuario.
     */
    @Column(name = "APELLIDO1", nullable = false, length = 50)
    private String apellido1;

    /**
     * Segundo apellido del usuario.
     */
    @Column(name = "APELLIDO2", length = 50)
    private String apellido2;

    /**
     * Correo electrónico del usuario.
     */
    @Column(name = "EMAIL", length = 100)
    private String email;

    /**
     * Fecha de alta del usuario.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_ALTA", nullable = false, length = 19)
    private Date fechaAlta;

    /**
     * Rol del usuario.
     */
    @ManyToOne
    @JoinColumn(name = "ID_ROL", nullable = false)
    private Rol rol;

    /**
     * Constructor por defecto.
     */
    public Usuario() {
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
    public Rol getRol() {
        return rol;
    }

    /**
     * Método que establece el rol del usuario.
     *
     * @param rol El rol del usuario.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Método que obtiene la clave primaria del usuario.
     *
     * @return Devuelve la clave primaria del usuario.
     */
    @Transient
    public Integer getPrimaryKey() {
        return getIdUsuario();
    }

    /**
     * Método que establece la clave primaria del usuario.
     *
     * @param pk La clave primaria del usuario.
     */
    @Transient
    public void setPrimaryKey(Integer pk) {
        setIdUsuario(pk);
    }

    /**
     * Método que obtiene el nombre de la clave primaria del usuario.
     *
     * @return Devuelve el nombre la clave primaria del usuario.
     */
    @Transient
    public String getPrimaryKeyName() {
        return "idUsuario";
    }

    /**
     * Método que indica si el usuario es nuevo (true) o no (false).
     *
     * @return Devuelve true si el usuario es nuevo, false en caso contrario.
     */
    @Transient
    public boolean isNew() {
        return getIdUsuario() == null;
    }
}

package com.irs.springmvcexporterwebapp.dominio;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Entidad base del dominio.
 *
 * @author IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable {

    /**
     * Constructor.
     */
    public BaseEntity() {
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

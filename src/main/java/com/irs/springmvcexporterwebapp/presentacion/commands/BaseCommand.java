package com.irs.springmvcexporterwebapp.presentacion.commands;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Comando base para Spring MVC.
 *
 * @author IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class BaseCommand implements Serializable {

    /**
     * Constructor.
     */
    public BaseCommand() {
        super();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

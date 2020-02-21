package com.irs.springmvcexporterwebapp.presentacion.util;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

public class ConfigurableFormattingConversionServiceFactoryBean extends
        FormattingConversionServiceFactoryBean {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigurableFormattingConversionServiceFactoryBean.class);

    /**
     * Constructor.
     */
    public ConfigurableFormattingConversionServiceFactoryBean() {
        super();
    }

    @Override
    public void setFormatterRegistrars(
            Set<FormatterRegistrar> formatterRegistrars) {
        super.setFormatterRegistrars(formatterRegistrars);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Estableciendo los formatter registrars");
        }
    }
}

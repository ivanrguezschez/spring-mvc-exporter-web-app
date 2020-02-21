package com.irs.springmvcexporterwebapp.presentacion.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class DateFormatterRegistrar implements FormatterRegistrar {

    private DateFormatter dateFormatter;

    @Autowired
    public DateFormatterRegistrar(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public void registerFormatters(FormatterRegistry registry) {
        registry.addFormatter(dateFormatter);
    }
}

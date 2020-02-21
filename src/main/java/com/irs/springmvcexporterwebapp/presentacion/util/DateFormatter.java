package com.irs.springmvcexporterwebapp.presentacion.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class DateFormatter extends org.springframework.format.datetime.DateFormatter {

    public static final String PATTERN_DATE_KEY = "pattern.date";

    @Autowired
    public DateFormatter(MessageSource messageSource) {
        super(messageSource.getMessage(PATTERN_DATE_KEY, null, null));
    }
}

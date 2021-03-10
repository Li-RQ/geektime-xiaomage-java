package org.geektimes.web.mvc.header;

import org.geektimes.web.mvc.header.annotation.Controller;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CacheControlHeaderWriter implements HeaderWriter {

    @Override
    public void write(Map<String, List<String>> headers, String... headerValues) {
        headers.put("cache-control", Arrays.asList(headerValues));
    }
}

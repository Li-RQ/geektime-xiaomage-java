package org.geektimes.web.mvc.converter;

import java.util.HashMap;
import java.util.Map;

public class ConverterManager {
    private static Map<String, Converter> converterMap = new HashMap<>();

    public static void addConverter(Object o) {
        String name = o.getClass().getName();
        converterMap.put(name, new Converter(o));
    }

    public static String converterToString(Object o) {
        String name = o.getClass().getName();
        Converter converter = converterMap.get(name);
        if (converter == null) {
            return o.toString();
        }
        return converter.converterToString(o);
    }

    public static <T> T converterToObject(String str, Class c) {
        if (!converterMap.containsKey(c.getName())) return null;
        return converterMap.get(c.getName()).converterToObject(str);
    }
}

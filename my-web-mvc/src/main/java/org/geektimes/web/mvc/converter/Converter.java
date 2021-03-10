package org.geektimes.web.mvc.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Converter {

    private Object object;
    private Method converterToString;
    private Method converterToObject;

    public Converter(Object o){
        this.object = o;
        for (Method m: o.getClass().getMethods()) {
            if (m.isAnnotationPresent(ConverterToString.class) && m.getReturnType() == String.class) {
                converterToString = m;
                continue;
            }
            if (m.isAnnotationPresent(ConverterToObject.class)) {
                converterToObject = m;
            }
        }
    }

    public String converterToString(Object o) {
        try {
            Object result = converterToString.invoke(object, o);
            if (result instanceof String) {
                return (String) result;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> T converterToObject(String str) {
        try {
            return (T) converterToObject.invoke(object, str);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}

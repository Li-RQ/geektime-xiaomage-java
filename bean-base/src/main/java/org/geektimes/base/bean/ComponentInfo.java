package org.geektimes.base.bean;

import java.lang.annotation.Annotation;

public class ComponentInfo {
    private String name;
    private Class instanceClass;
    private Annotation[] annotations;
    private Object instance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getInstanceClass() {
        return instanceClass;
    }

    public void setInstanceClass(Class instanceClass) {
        this.instanceClass = instanceClass;
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotation[] annotations) {
        this.annotations = annotations;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }
}

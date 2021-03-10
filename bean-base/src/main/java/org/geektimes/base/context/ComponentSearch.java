package org.geektimes.base.context;

import java.lang.annotation.Annotation;
import java.util.List;

public interface ComponentSearch {
    /**
     * 通过注解搜索组件列表
     * @param annotation
     * @return
     */
    public <T> List<T> getComponentByAnnotation(Class<? extends Annotation> annotation);

    /**
     * 通过类搜索组件列表
     * @param aClass
     * @return
     */
    public <T> List<T> getComponentByClass(Class aClass);

    /**
     * 通过定义名称搜索组件
     * @param name
     * @return
     */
    public <T> T getComponentByName(String name);
}

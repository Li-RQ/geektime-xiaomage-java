package org.geektimes.web.mvc.listener;

import org.geektimes.base.context.ComponentContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebComponentContext extends ComponentContext implements ServletContextListener {


    private static ServletContext servletContext; // 请注意
    private static ComponentContext componentContext;


    /**
     * 获取 ComponentContext
     *
     * @return
     */
    public static ComponentContext getInstance() {
        return (ComponentContext) servletContext.getAttribute(CONTEXT_NAME);
    }


    public void init(ServletContext servletContext) throws RuntimeException {
        WebComponentContext.servletContext = servletContext;
        // 获取当前 ServletContext（WebApp）ClassLoader
        componentContext = new ComponentContext();
        servletContext.setAttribute(CONTEXT_NAME, componentContext);
        componentContext.init(servletContext.getClassLoader());
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        init(servletContextEvent.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        destroy();
    }
}

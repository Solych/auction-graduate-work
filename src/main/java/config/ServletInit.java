package config;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Nullable
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                DbConfig.class, LogConfig.class
        };
    }

    @Nullable
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebAppImpl.class
        };
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

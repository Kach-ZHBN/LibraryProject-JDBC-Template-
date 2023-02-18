package by.kach.library.config;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.util.EnumSet;

/**
 * Конфигурация необходимая серверу.
 */
public class LibrarySpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * Указывае конфигурационный файл для нашего Spring-приложения. Возможно, это необходимо для
     * Dispather servlet'а
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }

    /**
     * Указываем, что абсолютно все поступающие URL-запросы Dispatcher servlet должен перенаправлять в
     * Spring-приложение.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    /**
     * Код ниже - кодя фильтра. Фильр требуется для того, чтобы обрабатывать все поступающие POST-запросы от
     * HTML форм, т.к. в HTML'е отсуствуют HTTP методы PATCH и DELETE (в этом случае создается скрытое поле (<input>)
     *и в нем уже описывается необходимый нам метод, который фильтр и пытается найти)
     */
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerCharacterEncodingFilter(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }

    /**
     * Весь код ниже необходим для возможности использования русского языка
     */
    private void registerCharacterEncodingFilter(ServletContext aContext) {
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistration.Dynamic characterEncoding = aContext.addFilter("characterEncoding", characterEncodingFilter);
        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }
}

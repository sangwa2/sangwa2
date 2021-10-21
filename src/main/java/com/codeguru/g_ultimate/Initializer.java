/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate;

import javax.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author SANGWA
 */
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
     @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {Config.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }
    
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration){
        boolean done=registration.setInitParameter("throwExceptionIfNoHandlerFound","true");
        if(!done) throw new RuntimeException();
    }
    
}

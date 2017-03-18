package com.epam.zubar.hr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Class  is a Filter that sets the 'UTF-8' character
 * encoding for Request and Response objects.
 * Without it DB gets ru characters as a code symbols.
 * @author Mikalay Zubar
 */

public class EncodingFilter implements Filter{

    private String code;

    @Override
    public void destroy() {
        code = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");
    }

}

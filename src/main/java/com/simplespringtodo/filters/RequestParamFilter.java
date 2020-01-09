package com.simplespringtodo.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplespringtodo.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * The type Request param filter.
 */
@WebFilter(filterName = "RequestParamFilter", urlPatterns = "/todoItems")
public class RequestParamFilter extends GenericFilterBean {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Gets object mapper.
     *
     * @return the object mapper
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    public void initFilterBean() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Pattern pattern = Pattern.compile("true|false", Pattern.CASE_INSENSITIVE);
        String completed = request.getParameter("completed");

        if (completed != null && !pattern.matcher(completed).matches()) {

            Map<String, Object> map = new HashMap<>();

            map.put("status", HttpStatus.BAD_REQUEST.value());
            map.put("message", "Invalid request param provided, value for completed must be boolean");

            ((HttpServletResponse) response).setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            this.getObjectMapper().writeValue(response.getWriter(), map);
        }

        chain.doFilter(request, response);
    }
}

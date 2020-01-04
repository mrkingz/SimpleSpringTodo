package com.simplespringtodo.filters;

import com.simplespringtodo.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = "/todoItems")
public class ValidateFilterParam implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Pattern pattern = Pattern.compile("true|false", Pattern.CASE_INSENSITIVE);

        //if (pattern.matcher(request.getParameter("completed")).matches()) {
            chain.doFilter(request, response);
//        } else {
//            throw new CustomException("wwwwwwww", HttpStatus.BAD_REQUEST);
//        }
    }

    @Override
    public void destroy() {

    }
}

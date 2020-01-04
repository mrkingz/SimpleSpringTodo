package com.simplespringtodo.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplespringtodo.models.Todo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * The type Reset request filter.
 */
@WebFilter(filterName = "ResetRequestFilter", urlPatterns = "/todos")
public class ResetRequestFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;

        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request) {

            @Override
            public ServletInputStream getInputStream() {
                ServletInputStream inputStream = null;
                StringBuilder stringBuilder = new StringBuilder();

                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(super.getInputStream()))) {

                    int bytesRead;
                    while ((bytesRead = bufferedReader.read()) > 0) {
                        stringBuilder.append((char) bytesRead);
                    }

                    ObjectMapper mapper = new ObjectMapper();
                    Todo todo = mapper.readValue(stringBuilder.toString().getBytes(), Todo.class);
                    // Perform operations on todo instance here

                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mapper.writeValueAsBytes(todo));
                    inputStream = new ServletInputStream() {

                        @Override
                        public boolean isFinished() {
                            return false;
                        }

                        @Override
                        public boolean isReady() {
                            return false;
                        }

                        @Override
                        public void setReadListener(ReadListener readListener) {

                        }

                        @Override
                        public int read() {
                            return byteArrayInputStream.read();
                        }
                    };

                } catch(IOException e) {
                    e.printStackTrace();
                }

                return inputStream;
            }
        };

        chain.doFilter(requestWrapper, resp);
    }

    public void init(FilterConfig config) throws ServletException {}

}



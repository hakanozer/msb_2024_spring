package com.works.configs;

import com.works.entities.Customer;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        String[] freeUrls = {"/customer/register", "/customer/login"};
        boolean loginStatus = true;

        for(String item : freeUrls) {
            if (item.equals(url)) {
                loginStatus = false;
                break;
            }
        }

        if (loginStatus) {
            // session control
            Object obj = req.getSession().getAttribute("customer");
            if (obj == null) {
                // session null
                PrintWriter printWriter = res.getWriter();
                printWriter.print("{ \"status\": true, \"message\": \"Plase Login\" }");
                res.setContentType("application/json");
                res.setStatus(HttpStatus.UNAUTHORIZED.value());
            }else {
                // session success
                filterChain.doFilter(req, res);
            }
        }else {
            filterChain.doFilter(req, res);
        }
    }

}

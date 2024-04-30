package com.works.configs;

import com.works.entities.Customer;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        String[] freeUrls = {"/", "/login"};
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
                res.sendRedirect("/");
            }else {
                // session success
                Customer c = (Customer) obj;
                req.setAttribute("customer", c);
                filterChain.doFilter(req, res);
            }
        }else {
            filterChain.doFilter(req, res);
        }
    }

}

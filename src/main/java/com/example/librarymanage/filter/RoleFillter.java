package com.example.librarymanage.filter;

import com.example.librarymanage.model.Role;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RoleFillter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        HttpSession session=request.getSession();

        String path=request.getRequestURI();

        if ((path.contains("/api/books") && request.getMethod().equals("GET"))||path.startsWith("/api/auth/")){
            chain.doFilter(request,response);
            return;
        }

        if (session == null|| session.getAttribute("role") == null){
            response.setStatus(401);
            response.getWriter().write("Ban phai dang nhap de tiep tuc");
            return;
        }

        Role role= (Role) session.getAttribute("role");

        if (path.contains("/api/books") && (request.getMethod().equals("POST") || request.getMethod().equals("PUT") || request.getMethod().equals("DELETE"))) {
            if (!role.equals(Role.ADMIN)){
                response.setStatus(403);
                response.getWriter().write("Khong co quyen truy cap");
                return;
            }else {
                chain.doFilter(request,response);
            }
        }

        if (path.equals("/api/accounts") && request.getMethod().equals("GET")){
            if (!role.equals(Role.ADMIN)) {
                response.setStatus(403);
                response.getWriter().write("Khong co quyen truy cap");
                return;
            }
        }

        if (path.equals("/api/records") && request.getMethod().equals("GET")){
            if (!role.equals(Role.ADMIN)) {
                response.setStatus(403);
                response.getWriter().write("Khong co quyen truy cap");
                return;
            }
        }

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}

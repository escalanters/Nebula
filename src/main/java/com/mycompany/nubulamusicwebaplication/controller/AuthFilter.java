package com.mycompany.nubulamusicwebaplication.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();

        HttpSession session = request.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute("usuario") != null);
        boolean loginRequest = path.contains("iniciar-sesion.jsp") || path.contains("registro.jsp") || path.contains("autenticacion");
        boolean resourceStaticRequest = path.contains("assets/") || path.contains("css") || path.contains("img");
        boolean apiRequest = path.contains("/api/"); // ✅ AGREGAR ESTO

        if (loggedIn || loginRequest || resourceStaticRequest || path.endsWith("tyc.jsp") || apiRequest) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(request.getContextPath() + "views/auth/iniciar-sesion.jsp");
        }
    }
}

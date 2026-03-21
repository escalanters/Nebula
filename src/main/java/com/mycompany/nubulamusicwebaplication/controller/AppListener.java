package com.mycompany.nubulamusicwebaplication.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext app = sce.getServletContext();
        app.setAttribute("empresa", "Nebula Music");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

package com.mycompany.nubulamusicwebaplication.controller;

import com.mycompany.nubulamusicwebaplication.service.IUsuarioService;
import com.mycompany.nubulamusicwebaplication.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "RegistroServlet", urlPatterns = {"/registro"})
public class RegistroServlet extends HttpServlet {
    private final IUsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("txt_correo");
        String contrasenia = request.getParameter("txt_contrasenia");
        String pseudonimo = request.getParameter("txt_pseudonimo");
        String estado = "activo";
        String tipoCuenta = request.getParameter("sel_cuenta");
        String fechaNacimientoStr = request.getParameter("txt_fechaNacimiento");
        String terminos = request.getParameter("chk_terminos");

        try {
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
            boolean aceptoTerminos = "aceptado".equals(terminos);

            usuarioService.registrar(nombre, correo, contrasenia, pseudonimo, estado, tipoCuenta, fechaNacimiento, aceptoTerminos);

            request.setAttribute("msg", "Registro exitoso");
            request.getRequestDispatcher("/views/auth/iniciar-sesion.jsp").forward(request, response);

        } catch (IllegalArgumentException ex) {
            request.setAttribute("error", ex.getMessage());
            throw new ServletException("Error al registrar el usuario");
        } catch (Exception ex) {
            throw new ServletException("Error al registrar el usuario");
        }
    }
}

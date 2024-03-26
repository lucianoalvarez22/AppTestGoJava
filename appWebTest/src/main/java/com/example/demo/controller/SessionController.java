package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
@SessionAttributes("username")
public class SessionController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Nombre de la plantilla Thymeleaf para el formulario de inicio de sesión
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, HttpSession session) {
        // Verifica las credenciales del usuario (aquí deberías tener tu lógica de autenticación)
        // Si las credenciales son válidas, guarda el nombre de usuario en la sesión
        session.setAttribute("username", username);
        return "redirect:/index"; // Redirige a la página principal después de iniciar sesión
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // Invalida la sesión
        return "redirect:/login"; // Redirige a la página de inicio de sesión después de cerrar sesión
    }

    // Ejemplo de una página protegida
    @GetMapping("/index")
    public String home(Model model) {
        // Verifica si el usuario ha iniciado sesión (podrías implementar una lógica de verificación más sofisticada aquí)
        if (!model.containsAttribute("username")) {
            return "redirect:/login"; // Redirige a la página de inicio de sesión si el usuario no ha iniciado sesión
        }
        return "index"; // Nombre de la plantilla Thymeleaf para la página principal
    }
}


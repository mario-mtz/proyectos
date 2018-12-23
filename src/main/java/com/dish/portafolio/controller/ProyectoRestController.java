/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dish.portafolio.controller;

import com.dish.portafolio.service.ProyectoService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mariomtz
 */
@RestController
public class ProyectoRestController {
    
    @Autowired
    private ProyectoService proyectoService;
    
    @GetMapping(path = "api/proyectos")
    public Map getProyectos() {
        return this.proyectoService.getAll();
    }
    
    @GetMapping(path = "api/auth/proyectos")
    public Map getProyectosAuth() {
        return this.proyectoService.getAll();
    }
    
}

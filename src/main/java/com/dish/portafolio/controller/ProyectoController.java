/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dish.portafolio.controller;

import com.dish.portafolio.model.Desarrollador;
import com.dish.portafolio.model.Proyecto;
import com.dish.portafolio.service.ProyectoService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @version 1.0
 * @author mariomtz
 */
@Controller
@SessionAttributes("proyecto")
public class ProyectoController {
    
    private List<Desarrollador> desarrolladores = new ArrayList<>();
    @Autowired private ProyectoService proyectoService;  
    
    /**
     * @author mariomtz
     * @return login view     
     */
    @GetMapping(path = {"/","/login"})
    public String login(){
        return "login";
    }
    
    /**
     * @author mariomtz
     * @return proyecto view   
     */
    @GetMapping(path = "/proyecto")
    public String proyecto(Model model){
        model.addAttribute("proyectos", this.proyectoService.findAll());
        return "proyecto";
    }
    
    /**
     * @author mariomtz
     * @return crearproyecto view 
     * regresa la pantalla inicial para crear proyectos
     */
    @GetMapping(path = "/proyecto/nuevo")
    public String nuevoProyectoInit(Model model){
        this.proyectoService.getInitNuevoProyecto(model, null);
        this.desarrolladores.clear();
        model.addAttribute("title", "Nuevo Proyecto");
        model.addAttribute("proyecto", new Proyecto());
        return "crearproyecto";
    }
    
    /**
     * @author mariomtz
     * @param idProyecto
     * @return crearproyecto view
     * regresa a la vista un proyecto seleccionado para editarlo
     */
    @GetMapping(path = "/proyecto/editar/{idProyecto}")
    public String editarProyecto(@PathVariable("idProyecto") Long idProyecto, Model model){
        Proyecto p = this.proyectoService.getProyecto(idProyecto);
        this.desarrolladores = p.getDesarrolladores();
        this.proyectoService.getInitNuevoProyecto(model, this.desarrolladores);
        model.addAttribute("proyecto", p);
        model.addAttribute("title", "Editar Proyecto");
        return "crearproyecto";
    }
    
    /**
     * @author mariomtz
     * @param idProyecto
     * @return crearproyecto view
     * regresa a la vista un proyecto seleccionado para consultarlo
     */
    @GetMapping(path = "/proyecto/consultar/{idProyecto}")
    public String getProyecto(@PathVariable("idProyecto") Long idProyecto, Model model){
        Proyecto p = this.proyectoService.getProyecto(idProyecto);
        model.addAttribute("proyecto", p);
        model.addAttribute("title", "Consultar Proyecto");
        return "crearproyecto";
    }
    
    /**
     * @author mariomtz
     * @param proyecto
     * @param status
     * @return redirect proyecto view
     * guarda un proyecto 
     */
    @PostMapping(path = "/proyecto")
    public String nuevoProyecto(@ModelAttribute Proyecto proyecto, SessionStatus status){
        this.proyectoService.guardarProyecto(proyecto);
        status.setComplete();
        this.desarrolladores.clear();
        return "redirect:proyecto";
    }
    
    /**
     * @author mariomtz
     * @param idProyecto
     * @return redirect proyecto view
     * elimina un proyecto seleccionado 
     */
    @GetMapping(path = "/proyecto/eliminar/{idProyecto}")
    public String delProyecto(@PathVariable("idProyecto")Long idProyecto){
        this.proyectoService.deleteProyecto(idProyecto);
        return "redirect:/proyecto";
    }
    
    /**
     * @author mariomtz
     * @param proyecto
     * @param req
     * @param model
     * @return crearproyecto view
     * agrega un desarrollador al proyecto  
     */
    @PostMapping(path = "/proyecto", params = {"addDesarrollador"})
    public String addDesarrollador(final Proyecto proyecto, final HttpServletRequest req, Model model){
        final Long idDesarrollador = Long.valueOf(req.getParameter("idDesarrollador"));                
        if(idDesarrollador > 0L) {
            Desarrollador ds = this.proyectoService.getDesarrollador(idDesarrollador);
            proyecto.setDesarrolladores(this.desarrolladores);
            this.desarrolladores.add(ds); 
           
        } 
        fillModel(model, proyecto);
        return "crearproyecto";
    }
    
    /**
     * @author mariomtz
     * @param proyecto
     * @param req
     * @param model
     * @return crearproyecto view
     * elimina un desarrollador al proyecto  
     */
    @PostMapping(path = "/proyecto", params = {"delDesarrollador"})
    public String delDesarrollador(final Proyecto proyecto, final HttpServletRequest req, Model model){
        final Long idDesarrollador = Long.valueOf(req.getParameter("delDesarrollador"));                
        Iterator i = this.desarrolladores.iterator();
        while(i.hasNext()) {
            Desarrollador d = (Desarrollador)i.next();
            if(d.getId().equals(idDesarrollador)){
                i.remove();
            }
        }
        proyecto.setDesarrolladores(this.desarrolladores);
        fillModel(model, proyecto);
        return "crearproyecto";
    } 
    
    private void fillModel(Model model, Proyecto proyecto) {
        this.proyectoService.getInitNuevoProyecto(model, this.desarrolladores);
        model.addAttribute("title", proyecto.getId() == null ? "Nuevo Proyecto" :"Editar Proyecto");
    }
}

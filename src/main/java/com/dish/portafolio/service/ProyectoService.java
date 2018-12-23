/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dish.portafolio.service;

import com.dish.portafolio.model.Desarrollador;
import com.dish.portafolio.model.Proyecto;
import java.util.List;
import java.util.Map;
import org.springframework.ui.Model;

/**
 *
 * @author mariomtz
 */
public interface ProyectoService {
    List<Proyecto> findAll();
    Map getAll();
    void getInitNuevoProyecto(Model model, List<Desarrollador> seleccionados);
    Proyecto getProyecto(Long idProyecto);
    void guardarProyecto(Proyecto proyecto);
    Desarrollador getDesarrollador(Long idDesarrollador);
    void deleteProyecto(Long idProyecto);
}

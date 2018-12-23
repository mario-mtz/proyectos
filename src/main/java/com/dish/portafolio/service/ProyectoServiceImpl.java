/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dish.portafolio.service;

import com.dish.portafolio.dao.IDesarrolladorDao;
import com.dish.portafolio.dao.IManagerDao;
import com.dish.portafolio.dao.IProyectoDao;
import com.dish.portafolio.model.Desarrollador;
import com.dish.portafolio.model.Proyecto;
import com.dish.portafolio.model.ProyectoDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

/**
 * @version 1.0
 * @author mariomtz
 */
@Service
public class ProyectoServiceImpl implements ProyectoService {
    
    @Autowired private IProyectoDao proyectoDao;
    @Autowired private IManagerDao managerDao;
    @Autowired private IDesarrolladorDao desarrolladorDao;
        
    /**
     * @author mariomtz
     * @return list of projects
     * regresa lista de proyectos sin asociaciones
     */
    @Transactional(readOnly = true)
    @Override
    public List<Proyecto> findAll(){
        return this.proyectoDao.findAll();
    }
    
    /**
     * @author mariomtz
     * @return list of projects
     * regresa lista de proyectos con asociaciones
     */
    @Transactional(readOnly = true)
    @Override
    public Map getAll(){
        List<Proyecto> ps = this.proyectoDao.findAll();
        List<ProyectoDTO> proyectos = new ArrayList<>();
        ps.forEach( p -> {
            final ProyectoDTO dto = new ProyectoDTO();
            dto.setClave(p.getClave());
            dto.setDescripcion(p.getDescripcion());
            dto.setManager(p.getManager().getNombre());
            List<Desarrollador> desarrolladores = this.desarrolladorDao.getDesarrolladoresByIdProyecto(p.getId());
            dto.setDesarrolladores(desarrolladores.stream().map( d -> d.getNombre()).collect(Collectors.toList()));
            proyectos.add(dto);
            
        });
        
        return Collections.singletonMap("proyectos", proyectos);
    }
    
    /**
     * @author mariomtz
     * @param model
     * @param seleccionados
     * llena la vista con catálogos iniciales
     */
    @Transactional(readOnly = true)
    @Override
    public void getInitNuevoProyecto(Model model, List<Desarrollador> seleccionados){
        model.addAttribute("managers", this.managerDao.findAll());
        List<Desarrollador> l = this.desarrolladorDao.findAll();
        if(seleccionados != null) {
            l.removeAll(seleccionados);
        }
        model.addAttribute("desarrolladores", l);
    }
    
    /**
     * @author mariomtz
     * @param idDesarrollador
     * @return desarrollador
     * busca un desarrollador por id
     */
    @Transactional(readOnly = true)
    @Override
    public Desarrollador getDesarrollador(Long idDesarrollador){
        return this.desarrolladorDao.findById(idDesarrollador).get();
    }
    
    /**
     * @author mariomtz
     * @param idProyecto
     * @return proyecto
     * busca un proyecto por id
     */
    @Transactional(readOnly = true)
    @Override
    public Proyecto getProyecto(Long idProyecto){
        Proyecto p = this.proyectoDao.findById(idProyecto).get();
        List<Desarrollador> desarrolladores = this.desarrolladorDao.getDesarrolladoresByIdProyecto(idProyecto);
        p.setDesarrolladores(desarrolladores);
        return p;
    }
    
    /**
     * @author mariomtz
     * @param proyecto
     * guarda y actualiza un proyecto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void guardarProyecto(Proyecto proyecto) {
        try {
            List<Desarrollador> ds = proyecto.getDesarrolladores();
            proyecto.setDesarrolladores(new ArrayList<>());
            if(proyecto.getId() !=   null) {
                Proyecto p = this.proyectoDao.findById(proyecto.getId()).get();
                p.setClave(proyecto.getClave());
                p.setDescripcion(proyecto.getDescripcion());
                p.setManager(proyecto.getManager());
                proyecto = p;
            }
            this.proyectoDao.save(proyecto);
            for(Desarrollador d : ds) {
                d = this.desarrolladorDao.getById(d.getId());
                d.getProyectos().add(proyecto);
            }
            proyecto.setDesarrolladores(ds);
            this.proyectoDao.save(proyecto);
        } catch (DataAccessException e) {
            throw new RuntimeException(String.format("Error al guardar proyecto ex : ", e));
        }
        
    }
    
    /**
     * @author mariomtz
     * @param idproyecto
     * elimina un proyecto por id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteProyecto(Long idproyecto) {
        try {
            this.proyectoDao.deleteById(idproyecto);
        } catch (DataAccessException e) {
            throw new RuntimeException(String.format("Error al eliminar proyecto ex : ", e));
        }
    }
    
    
}

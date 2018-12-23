/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dish.portafolio.dao;

import com.dish.portafolio.model.Desarrollador;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @version 1.0
 * @author mariomtz
 * Interfaz dao para la entidad desarrollador
 */
public interface IDesarrolladorDao extends JpaRepository<Desarrollador, Long>{
    
    /**
     * @author mariomtz
     * @param id
     * @return Desarrollador
     * busca un desarrollador por id
     */
    @Query("select d from Desarrollador d left join fetch d.proyectos where d.id=:id")
    Desarrollador getById(@Param("id")Long id);
    
    /**
     * @author mariomtz
     * @param id
     * @return lista de Desarrollador
     * busca una lista desarrolladores asociados a un proyecto
     */
    @Query("select d from Desarrollador d left join fetch d.proyectos p where p.id=:id")
    List<Desarrollador> getDesarrolladoresByIdProyecto(@Param("id")Long id);
}

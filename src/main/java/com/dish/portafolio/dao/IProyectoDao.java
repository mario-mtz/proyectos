/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dish.portafolio.dao;

import com.dish.portafolio.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @author mariomtz
 * Interfaz dao para la Proyecto desarrollador
 */
public interface IProyectoDao extends JpaRepository<Proyecto, Long> {
    
}

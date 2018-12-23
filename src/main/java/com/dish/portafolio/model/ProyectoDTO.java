/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dish.portafolio.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @version 1.0
 * @author mariomtz
 */
@Data
public class ProyectoDTO implements Serializable{
    private String clave;
    private String descripcion;
    private String manager;
    List<String> desarrolladores;
}

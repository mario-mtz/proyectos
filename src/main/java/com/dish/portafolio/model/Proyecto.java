/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dish.portafolio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * @version 1.0
 * @author mariomtz
 */
@Data
@Entity
@Table(name = "proyecto")
public class Proyecto implements Serializable {
    
    private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clave;
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Manager manager;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)  
    @JoinTable(name = "proyecto_desarrollador", 
            joinColumns = @JoinColumn(name = "id_proyecto"), 
            inverseJoinColumns = @JoinColumn(name = "id_desarrollador"))        
    List<Desarrollador> desarrolladores;
    
    public Proyecto() {
        this.manager = new Manager();
        this.desarrolladores = new ArrayList<>();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proyecto)) return false;
        Proyecto d = (Proyecto) o;
        return id != null && id.equals(d.id);
    }
 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
}

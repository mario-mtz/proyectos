/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dish.project;

import com.dish.portafolio.PortafolioApplication;
import com.dish.portafolio.dao.IDesarrolladorDao;
import com.dish.portafolio.dao.IManagerDao;
import com.dish.portafolio.dao.IProyectoDao;
import com.dish.portafolio.model.Desarrollador;
import com.dish.portafolio.model.Manager;
import com.dish.portafolio.model.Proyecto;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mariomtz
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PortafolioApplication.class})
public class TestProyecto {
    
    @Autowired
    private IProyectoDao proyectoDao;
    
    @Autowired
    private IManagerDao managerDao;
    
    @Autowired
    private IDesarrolladorDao desarrolladorDao;
    
    @Test
    public void testProyectoDaoOperations() {
        
       Manager m = new Manager();
       m.setNombre("Juan Aguilar");
       this.managerDao.save(m);
       
       Proyecto p = new Proyecto();
       p.setClave("C1");
       p.setDescripcion("Proyecto 1");
       p.setManager(m);
       this.proyectoDao.save(p);
       
       Desarrollador d =  new Desarrollador();
       d.setNombre("Mario Martinez");
       d.getProyectos().add(p);
       p.getDesarrolladores().add(d);
              
       this.desarrolladorDao.save(d);       
       this.proyectoDao.save(p);
       
       assertThat(p.getId()).isNotNull();       
       
       List<Proyecto> proyectos = this.proyectoDao.findAll();
       assertThat(proyectos).isNotNull();
       assertThat(proyectos).asList().isNotEmpty();
       
       assertThat(proyectos.get(0).getManager()).isNotNull();
               
       
    }
    
}

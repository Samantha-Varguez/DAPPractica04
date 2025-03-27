
package org.uv.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// add entity table and serializable 
@Entity
@Table(name="productos")
public class Productos implements Serializable {
    
    @Id
     @GeneratedValue(
            generator="productos_id_seq", 
            strategy = GenerationType.SEQUENCE)
      @SequenceGenerator(name="productos_id_seq", 
              sequenceName = "productos_id_seq", 
              initialValue=1, 
              allocationSize=1)
    
    @Column
    private Long clave;
    @Column
    private String nombre;
    @Column
    private double precio;
    
@JsonIgnore
    @OneToMany(mappedBy = "productos", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Productos> lstProductos;
    
    @OneToMany(mappedBy = "idventas", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<VentaDet> lstVentaDet;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<VentaDet> getLstVentaDet() {
        return lstVentaDet;
    }

    public void setLstVentaDet(List<VentaDet> lstVentaDet) {
        this.lstVentaDet = lstVentaDet;
    }
    
    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Productos> getLstProductos() {
        return lstProductos;
    }

    public void setLstProductos(List<Productos> lstProductos) {
        this.lstProductos = lstProductos;
    }
    
    
    
}

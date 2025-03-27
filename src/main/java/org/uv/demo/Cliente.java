
package org.uv.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    
    @Id
     @GeneratedValue(
            generator="clientes_id_seq", 
            strategy = GenerationType.SEQUENCE)
      @SequenceGenerator(name="clientes_id_seq", 
              sequenceName = "clientes_id_seq", 
              initialValue=1, 
              allocationSize=1)
    
    @Column
    private Long Id;
    @Column
    private String nombre;
    
     @OneToMany
        (mappedBy = "cliente", fetch= FetchType.LAZY, 
                cascade = CascadeType.ALL, orphanRemoval = false)
     
    @JsonIgnore
    private List<Venta> lstVenta;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public List<Venta> getLstVenta() {
        return lstVenta;
    }

    public void setLstVenta(List<Venta> lstVenta) {
        this.lstVenta = lstVenta;
    }

     
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}


package org.uv.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ventadet")
public class VentaDet implements Serializable {
 
    @Id
    @GeneratedValue(
            generator = "ventadet_idlinea_seq",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "ventadet_idlinea_seq",
            sequenceName = "ventadet_idlinea_seq",
            initialValue = 1,
            allocationSize = 1)
   @Column
   private Long idrow;
    @Column
   private BigDecimal precio;
    @Column
   private String descripcion;
    @Column
   private Long cantidad;
   
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "idproducto", nullable= false)
    private Productos productos;
   
   @ManyToOne
   @JoinColumn(name = "idventa")
   @JsonIgnore
   private Venta venta;
   
    public Long getIdrow() {
        return idrow;
    }

    public void setIdrow(Long idrow) {
        this.idrow = idrow;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
   
   
         
}

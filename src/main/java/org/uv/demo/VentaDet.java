
package org.uv.demo;

import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class VentaDet {
    
   private Long idrow;
   private Long producto;
   private Long precio;
   private String descripcion;
   private Long cantidad;
   
   @ManyToOne
   @JoinColumn(name = "producto")
    private Productos productos;
   
   @ManyToOne
   @JoinColumn(name = "idventa")
   private Venta idventa;
   
   

    public Long getIdrow() {
        return idrow;
    }

    public void setIdrow(Long idrow) {
        this.idrow = idrow;
    }

    public Venta getIdventa() {
        return idventa;
    }

    public void setIdventa(Venta idventa) {
        this.idventa = idventa;
    }


   
    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public Long getProducto() {
        return producto;
    }

    public void setProducto(Long producto) {
        this.producto = producto;
    }


    

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
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

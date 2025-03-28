
package org.uv.demo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "venta")
public class Venta implements Serializable {
    
    @Id
     @GeneratedValue(
            generator="venta_id_seq", 
            strategy = GenerationType.SEQUENCE)
      @SequenceGenerator
        (name="venta_id_seq", 
              sequenceName = "venta_id_seq", 
              initialValue=1, 
              allocationSize=1)
    
@Column
private Long id;
@Column
private LocalDate fecha;
@Column(precision = 9, scale = 6)
private BigDecimal monto;


@ManyToOne
   @JoinColumn(name = "idcliente")
    private Cliente cliente;

@OneToMany(mappedBy = "venta", 
        fetch = FetchType.LAZY, 
        cascade = CascadeType.ALL, 
        orphanRemoval = true)
private List<VentaDet> lstDetalleVenta;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<VentaDet> getLstDetalleVenta() {
        return lstDetalleVenta;
    }

    public void setLstDetalleVenta(List<VentaDet> lstDetalleVenta) {
        this.lstDetalleVenta = lstDetalleVenta;
    }

    
}

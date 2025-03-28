
package org.uv.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class DTOVenta {
 private Long clave;
 private LocalDate fecha;
 private BigDecimal monto;
 private Long IdCliente;
 private List<VentaDet> lstDetalleVenta;

    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
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

    public Long getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(Long IdCliente) {
        this.IdCliente = IdCliente;
    }

    public List<VentaDet> getLstDetalleVenta() {
        return lstDetalleVenta;
    }

    public void setLstDetalleVenta(List<VentaDet> lstDetalleVenta) {
        this.lstDetalleVenta = lstDetalleVenta;
    }
 
 
}


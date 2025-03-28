
package org.uv.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesVentas {
@Autowired
    RepositoryVenta repositoryVenta;

    public List<DTOVenta> fillDTOVenta() {
        List<Venta> ventas = repositoryVenta.findAll(); // Fetch ventas from DB
        List<DTOVenta> DTOVentas = new ArrayList<>();

        for (Venta venta : ventas) {
            DTOVenta dtoVenta = new DTOVenta();
            dtoVenta.setClave(venta.getId());
            dtoVenta.setFecha(venta.getFecha());
            dtoVenta.setIdCliente(venta.getCliente().getId());
            dtoVenta.setMonto(venta.getMonto());
            dtoVenta.setLstDetalleVenta(venta.getLstDetalleVenta()); 

            DTOVentas.add(dtoVenta);
        }

        return DTOVentas;
    }
}

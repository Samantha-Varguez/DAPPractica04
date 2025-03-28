package org.uv.demo;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/ventas")
public class ControllerVenta {

    @Autowired
    RepositoryCliente repositoryCliente;

    @Autowired
    RepositoryVenta repositoryVenta;

    @Autowired
    private ServicesVentas serviceVentas;

    @GetMapping()
    public List<DTOVenta> list() {
        List<DTOVenta> DTOVentas = serviceVentas.fillDTOVenta();
        System.out.println("Ventas found: " + DTOVentas.size());

        return DTOVentas;
    }

@GetMapping("/{id}")
public ResponseEntity<DTOVenta> getById(@PathVariable Long id) {
    Optional<Venta> ventaOptional = repositoryVenta.findById(id);
    if (ventaOptional.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Venta venta = ventaOptional.get();
    DTOVenta dtoVenta = new DTOVenta();
    
    // Map fields from Venta to DTOVenta
    dtoVenta.setClave(venta.getId());
    dtoVenta.setFecha(venta.getFecha());
    dtoVenta.setIdCliente(venta.getCliente().getId());
    dtoVenta.setMonto(venta.getMonto());
    
    // Convert VentaDet list to DTO format
    List<VentaDet> detalleList = new ArrayList<>();
    for (VentaDet detalle : venta.getLstDetalleVenta()) {
        VentaDet dtoDetalle = new VentaDet();
        dtoDetalle.setDescripcion(detalle.getDescripcion());
        dtoDetalle.setCantidad(detalle.getCantidad());
        dtoDetalle.setPrecio(detalle.getPrecio());

        Productos dtoProducto = new Productos();
        dtoProducto.setNombre(detalle.getProductos().getNombre());
        dtoProducto.setPrecio(detalle.getProductos().getPrecio());

        dtoDetalle.setProductos(dtoProducto);
        detalleList.add(dtoDetalle);
    }

    dtoVenta.setLstDetalleVenta(detalleList);

    return ResponseEntity.ok(dtoVenta);
}

    
    
    @PutMapping("/{id}")
    public ResponseEntity<DTOVenta> update(@PathVariable Long id, @RequestBody DTOVenta dtoventa) {
        Optional<Venta> ventaOptional = repositoryVenta.findById(id);
        if (ventaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Venta venta = ventaOptional.get();
        Optional<Cliente> ClienteOpcional = repositoryCliente.findById(dtoventa.getIdCliente());
        if (ClienteOpcional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = ClienteOpcional.get();

        // Update fields
        venta.setFecha(dtoventa.getFecha());
        venta.setCliente(cliente);
        venta.setMonto(dtoventa.getMonto());
        venta.getLstDetalleVenta().clear();

        for (VentaDet detalleVenta : dtoventa.getLstDetalleVenta()) {
            Productos producto = new Productos();
            producto.setNombre(detalleVenta.getProductos().getNombre());
            producto.setPrecio(detalleVenta.getProductos().getPrecio());

            detalleVenta.setProductos(producto);
            detalleVenta.setVenta(venta);
            venta.getLstDetalleVenta().add(detalleVenta);
        }

        Venta updatedVenta = repositoryVenta.save(venta);
        dtoventa.setClave(updatedVenta.getId());

        return ResponseEntity.ok(dtoventa);
    }

    @PostMapping
    public ResponseEntity<DTOVenta> post(@RequestBody DTOVenta dtoventa) {
        Optional<Cliente> ClienteOpcional = repositoryCliente.findById(dtoventa.getIdCliente());
        if (ClienteOpcional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = ClienteOpcional.get();

        Venta venta = new Venta();
        venta.setFecha(dtoventa.getFecha());
        venta.setCliente(cliente);
        venta.setMonto(dtoventa.getMonto());
        venta.setLstDetalleVenta(dtoventa.getLstDetalleVenta());

        for (VentaDet detalleVenta : venta.getLstDetalleVenta()) {
            Productos producto = new Productos();
            producto.setNombre(detalleVenta.getProductos().getNombre());
            producto.setPrecio(detalleVenta.getProductos().getPrecio());

            detalleVenta.setProductos(producto);
            detalleVenta.setVenta(venta);
        }
        Venta ventaNew = repositoryVenta.save(venta);
        dtoventa.setClave(ventaNew.getId());

        return ResponseEntity.ok(dtoventa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repositoryVenta.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repositoryVenta.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

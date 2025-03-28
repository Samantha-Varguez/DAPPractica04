
package org.uv.demo;

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
    
    private ServicesVentas serviceVentas = new ServicesVentas();
    
    @GetMapping()
    public List <DTOVenta> list() {
        List<DTOVenta> DTOVentas = serviceVentas.fillDTOVenta();
        return DTOVentas;
    }
    
//    @GetMapping("/{id}")
//    public Object get(@PathVariable String id) {
//        return null;
//    }
//    
//    @PutMapping("/{id}")
//    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
//        return null;
//    }
    
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
                
        for (VentaDet detalleVenta : venta.getLstDetalleVenta()){
            detalleVenta.setVenta(venta);
        }
        Venta ventaNew= repositoryVenta.save(venta);
        dtoventa.setClave(ventaNew.getId());
        
        return ResponseEntity.ok(dtoventa);
    }
    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) {
//        return null;
//    }
//    
}

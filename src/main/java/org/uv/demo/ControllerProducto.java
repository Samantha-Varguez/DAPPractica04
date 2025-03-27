
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
@RequestMapping("/productos")
public class ControllerProducto {
    
    @Autowired
    RepositoryProducto repositoryProductos;
    
    @GetMapping()
    public List<Productos> list() {
        return repositoryProductos.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Productos> get(@PathVariable Long id) {
       Optional<Productos> producto = repositoryProductos.findById(id);
        return producto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Productos> put(@PathVariable Long id, @RequestBody Productos input) {
        Optional <Productos> resprod=repositoryProductos.findById(id);
        if (resprod.isPresent()) {
            Productos productosToEdit = resprod.get();
            
            productosToEdit.setNombre(input.getNombre());
            productosToEdit.setPrecio(input.getPrecio());
            Productos productEdited= repositoryProductos.save(productosToEdit);
        }
            
        return null;
    }
    
    @PostMapping
    public ResponseEntity<Productos> post(@RequestBody Productos productos) {
       Productos productNew = repositoryProductos.save(productos);
       return ResponseEntity.ok(productNew);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Productos> delete(@PathVariable Long id) {
        
        Optional <Productos> resProd = repositoryProductos.findById(id);
        if (resProd.isPresent()){
            Productos prodToDelete=resProd.get();
            repositoryProductos.delete(prodToDelete);
            return ResponseEntity.ok(prodToDelete);
            
        }
        else 
            return ResponseEntity.notFound().build();
        
    }
    
}

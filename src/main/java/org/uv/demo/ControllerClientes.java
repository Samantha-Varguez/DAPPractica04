
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
@RequestMapping("/cliente")
public class ControllerClientes {
    
     @Autowired
    RepositoryCliente repositoryCliente;
    
    @GetMapping()
    public List<Cliente> list() {
        return repositoryCliente.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable Long id) {
       Optional<Cliente> cliente = repositoryCliente.findById(id);
       if (cliente.isPresent()){
           return ResponseEntity.ok(cliente.get());
       }
       else return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> put(@PathVariable Long id, @RequestBody Productos input) {
        Optional <Cliente> resprod=repositoryCliente.findById(id);
        if (resprod.isPresent()) {
            Cliente clientesToEdit = resprod.get();
            
            clientesToEdit.setNombre(input.getNombre());
            Cliente clientEdited= repositoryCliente.save(clientesToEdit);
        }
            
        return null;
    }
    
    @PostMapping
    public ResponseEntity<Cliente> post(@RequestBody Cliente cliente) {
       Cliente clientNew = repositoryCliente.save(cliente);
       return ResponseEntity.ok(clientNew);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        
        Optional <Cliente> resProd = repositoryCliente.findById(id);
        if (resProd.isPresent()){
            Cliente prodToDelete=resProd.get();
            repositoryCliente.delete(prodToDelete);
            return ResponseEntity.ok(prodToDelete);
            
        }
        else 
            return ResponseEntity.notFound().build();
        
    }
    
    
}

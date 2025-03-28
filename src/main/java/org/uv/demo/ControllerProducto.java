package org.uv.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> put(@PathVariable Long id, @RequestBody Productos input) {
        Optional<Productos> resprod = repositoryProductos.findById(id);
        if (resprod.isPresent()) {
            Productos productosToEdit = resprod.get();
            productosToEdit.setNombre(input.getNombre());
            productosToEdit.setPrecio(input.getPrecio());
            Productos productEdited = repositoryProductos.save(productosToEdit);
            return ResponseEntity.ok(productEdited);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Productos> post(@RequestBody Productos productos) {
        Productos productNew = repositoryProductos.save(productos);
        return ResponseEntity.ok(productNew);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Productos> delete(@PathVariable Long id) {

        if (!repositoryProductos.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositoryProductos.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {

    }

}

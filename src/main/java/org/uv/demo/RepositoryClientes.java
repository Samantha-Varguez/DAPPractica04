package org.uv.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryClientes extends JpaRepository <Cliente, Long> {
    
}

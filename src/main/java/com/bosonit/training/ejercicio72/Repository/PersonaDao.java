package com.bosonit.training.ejercicio72.Repository;

import com.bosonit.training.ejercicio72.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaDao extends JpaRepository<Persona, Integer> {
    List<Persona> findByname(String name);

}

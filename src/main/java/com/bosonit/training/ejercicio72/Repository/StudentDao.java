package com.bosonit.training.ejercicio72.Repository;

import com.bosonit.training.ejercicio72.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<Student, String> {


}

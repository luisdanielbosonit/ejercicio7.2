package com.bosonit.training.ejercicio72.Repository;

import com.bosonit.training.ejercicio72.Entity.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSubjectDao extends JpaRepository<StudentSubject,String> {
}

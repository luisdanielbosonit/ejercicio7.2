package com.bosonit.training.ejercicio72.Repository;

import com.bosonit.training.ejercicio72.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao extends JpaRepository<Teacher,String> {
}

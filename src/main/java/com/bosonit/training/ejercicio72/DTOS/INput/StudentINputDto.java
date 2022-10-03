package com.bosonit.training.ejercicio72.DTOS.INput;

import com.bosonit.training.ejercicio72.Entity.Persona;
import com.bosonit.training.ejercicio72.Entity.Student;
import com.bosonit.training.ejercicio72.Entity.Teacher;
import lombok.Data;

/**
 * A DTO for the {@link Student} entity
 */
@Data
public class StudentINputDto {

    private Integer id_persona;
    private String id_student;
    private String id_teacher;
    private int num_hours_week;
    private String coments;
    private String branch;

    public Student tranfomINputStuden(Persona persona, Teacher teacher) {
        Student student = new Student();
        student.setPersona(persona);
        student.setNum_hours_week(this.num_hours_week);
        student.setComents(this.coments);
        student.setTeacher(teacher);
        student.setBranch(this.branch);

        return student;

    }
}
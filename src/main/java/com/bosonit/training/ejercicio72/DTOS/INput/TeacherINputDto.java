package com.bosonit.training.ejercicio72.DTOS.INput;

import com.bosonit.training.ejercicio72.Entity.Persona;
import com.bosonit.training.ejercicio72.Entity.Teacher;
import lombok.Data;

/**
 * A DTO for the {@link Teacher} entity
 */
@Data
public class TeacherINputDto  {


    private Integer id_persona;
    private String coments;
    private String branch;

    public Teacher transformIntoTeacher(Persona persona){
        Teacher teacher=new Teacher();
        teacher.setPersona(persona);
        teacher.setComents(coments);
        teacher.setBranch(branch);

        return teacher;
    }


}
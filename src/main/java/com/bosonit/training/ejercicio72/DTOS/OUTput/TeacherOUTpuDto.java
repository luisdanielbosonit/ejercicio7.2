package com.bosonit.training.ejercicio72.DTOS.OUTput;

import com.bosonit.training.ejercicio72.Entity.Teacher;
import lombok.Data;

/**
 * A DTO for the {@link Teacher} entity
 */
@Data
public class TeacherOUTpuDto {

    private PersonaOUTputDto persona;
    private String id_teacher;
    private String coments;
    private String branch;

    public TeacherOUTpuDto(Teacher teacher){

        this.setPersona(new PersonaOUTputDto(teacher.getPersona()));
        this.id_teacher=teacher.getId_teacher();
        this.coments=teacher.getComents();
        this.branch=teacher.getBranch();

    }

}
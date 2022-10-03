package com.bosonit.training.ejercicio72.DTOS.OUTput;

import com.bosonit.training.ejercicio72.Entity.Student;
import lombok.Data;

/**
 * A DTO for the {@link Student} entity
 */
@Data
public class StudentOUTputDto {
    private String id_student;
    private PersonaOUTputDto id_persona;
    private int num_hours_week;
    private String coments;
    private String branch;
    private TeacherOUTpuDto id_teacher;


    public StudentOUTputDto (Student student){

            setId_student(student.getId_student());
            setId_teacher(student.getTeacher() != null ? new TeacherOUTpuDto(student.getTeacher()) : null);
            setId_persona(new PersonaOUTputDto((student.getPersona())));
            setNum_hours_week(student.getNum_hours_week());
            setComents(student.getComents());
            setBranch(student.getBranch());


        }

    }
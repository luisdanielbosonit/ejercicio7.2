package com.bosonit.training.ejercicio72.DTOS.OUTput;

import com.bosonit.training.ejercicio72.Entity.StudentSubject;
import lombok.Data;

/**
 * A DTO for the {@link StudentSubject} entity
 */
@Data
public class StudentSubjectOUTputDtoFORStudent {
    private String id_subject;
    private TeacherOUTpuDto Id_teacher;
    private String nameSubject;


    public StudentSubjectOUTputDtoFORStudent(StudentSubject studentSubject){
        setId_subject(studentSubject.getId_subject());
        setId_teacher(new TeacherOUTpuDto(studentSubject.getTeacher()));
        setNameSubject(studentSubject.getNameSubject());
    }
}
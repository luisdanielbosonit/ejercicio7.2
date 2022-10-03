package com.bosonit.training.ejercicio72.DTOS.OUTput;

import com.bosonit.training.ejercicio72.Entity.Student;
import com.bosonit.training.ejercicio72.Entity.StudentSubject;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link StudentSubject} entity
 */
@Data
public class StudentSubjectOUTputDto {
    private String id_subject;
    private TeacherOUTpuDto teacher;
    private String nameSubject;
    private Date created_date;
    private Date termination_date;
    private List<String>id_student= new ArrayList<>();


    public StudentSubjectOUTputDto(StudentSubject studentSubject){

        setId_subject(studentSubject.getId_subject());
        setTeacher(studentSubject.getTeacher()!=null ? new TeacherOUTpuDto(studentSubject.getTeacher()):null);
        setNameSubject(studentSubject.getNameSubject());
        setCreated_date(studentSubject.getCreated_date());
        setTermination_date(studentSubject.getTermination_date());
        setId_student(studentSubject.getStudents().stream().map(Student::getId_student).collect(Collectors.toList()));

    }

}
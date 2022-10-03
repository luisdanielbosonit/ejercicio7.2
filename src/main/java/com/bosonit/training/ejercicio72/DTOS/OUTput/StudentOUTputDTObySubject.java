package com.bosonit.training.ejercicio72.DTOS.OUTput;

import com.bosonit.training.ejercicio72.Entity.Student;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link Student} entity
 */
@Data
public class StudentOUTputDTObySubject {
    private String id_student;
    private PersonaOUTputDto persona;
    private int num_hours_week;
    private String coments;
    private TeacherOUTpuDto teacher;
    private String branch;
    private List<StudentSubjectOUTputDtoFORStudent> Subjects;

    public StudentOUTputDTObySubject(Student student){
        setId_student(student.getId_student());
        setPersona(new PersonaOUTputDto((student.getPersona())));
        setTeacher(student.getTeacher() != null ? new TeacherOUTpuDto(student.getTeacher()) : null);
        setNum_hours_week(student.getNum_hours_week());
        setComents(student.getComents());
        setBranch(student.getBranch());

        List<StudentSubjectOUTputDtoFORStudent> subjectsOutputDto = student.getStudentSubjects().stream().map(StudentSubjectOUTputDtoFORStudent::new).collect(Collectors.toList());
        setSubjects(subjectsOutputDto);
    }
}
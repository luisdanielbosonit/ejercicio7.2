package com.bosonit.training.ejercicio72.controller;

import com.bosonit.training.ejercicio72.DTOS.INput.StudentSubjectINputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.StudentSubjectOUTputDto;
import com.bosonit.training.ejercicio72.Service.implement.ServiceImplStudentSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentSubject")
public class StudentSubjectController {

    @Autowired
    ServiceImplStudentSubject serviceImplStudentSubject;

    @PostMapping("/upload")
    public StudentSubjectOUTputDto loadStudentSubject(@RequestBody StudentSubjectINputDto studentSubjectINputDto)throws Exception{
        return serviceImplStudentSubject.loadStudentSubject(studentSubjectINputDto);
    }

    @GetMapping("/{id_subject}")
    public StudentSubjectOUTputDto getIdSubject( @PathVariable String id_Subject)throws Exception{
        return serviceImplStudentSubject.viewIdStudentSubject(id_Subject);
    }

    @DeleteMapping("/{id_subject}")
    public void deleteSubjetc(@PathVariable String id_subject) throws Exception{
        serviceImplStudentSubject.deleteStudentSubject(id_subject);
    }
    @GetMapping("todos")
    public List<StudentSubjectOUTputDto> viewAll(){
        return (List<StudentSubjectOUTputDto>) serviceImplStudentSubject.viewAllStudentSubject();
    }

    @GetMapping("asignaturas/{id_student}")
    public List< StudentSubjectOUTputDto> gedSubjectStudentd(@PathVariable String id_student){
        return serviceImplStudentSubject.subjectbyId(id_student);
    }


}

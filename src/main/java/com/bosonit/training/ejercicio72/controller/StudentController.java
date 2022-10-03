package com.bosonit.training.ejercicio72.controller;

import com.bosonit.training.ejercicio72.DTOS.INput.StudentINputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.StudentOUTputDTObySubject;
import com.bosonit.training.ejercicio72.DTOS.OUTput.StudentOUTputDto;
import com.bosonit.training.ejercicio72.Service.implement.ServiceImplStudentSubject;
import com.bosonit.training.ejercicio72.Service.implement.ServiceImpl_Student;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    ServiceImpl_Student serviceStuden;
    @Autowired
    ServiceImplStudentSubject serviceImplStudentSubjectDAo;

    @PostMapping("/upload")
    public StudentOUTputDto loadstudent(@RequestBody StudentINputDto studentINputDto) throws Exception{
        return serviceStuden.loadStudent(studentINputDto);
    }

    @GetMapping("/{id_student}")
    public StudentOUTputDto viewIdStuden(@PathVariable String id_student, @RequestParam(required = false)@NotNull String outputType) throws Exception {
        StudentOUTputDto studentOUTputDto= serviceStuden.viewIdStudent(id_student);
        if (outputType==null)
            outputType="simple";

        if(outputType.equals("full"))
            return studentOUTputDto;

        studentOUTputDto.setId_persona(null);

        //return student;
     return studentOUTputDto;
    }

    @PutMapping("/{id_student}")
    public StudentOUTputDto updatestudent(@PathVariable String id_student, @RequestBody StudentINputDto studentINputDto) throws Exception{
        return serviceStuden.updateStudent(id_student,studentINputDto);


    }

    @DeleteMapping("/delete/{id_student}")
    public void deleteStudent(@PathVariable String id_student) throws Exception {
        StudentOUTputDto studentdelete = serviceStuden.viewIdStudent(id_student);
        serviceStuden.deleteStudent(id_student);
    }
    @GetMapping("/todos")
    public List<StudentOUTputDto> viewall(){
        return (List<StudentOUTputDto>) serviceStuden.viewAllStudent();
    }

    @GetMapping("/subject/{id_student}")
    public StudentOUTputDTObySubject studentandsubject(@PathVariable String id_student){
        return serviceStuden.studenbysubject(id_student,serviceImplStudentSubjectDAo.viewAllStudentSubject());
    }

    @PutMapping("/subject/{id_student}")
    public String addSubjectToStudent(@PathVariable String id_student, @RequestBody List<String> id_subjects){
        return serviceStuden.addSubjectToStudent(id_student, id_subjects);

    }
    @DeleteMapping("/student/{id_student}/subjects")
    public String deleteSubjectFromStudent(@PathVariable String id_student, @RequestBody List<String> id_subjects){
        return serviceStuden.deleteSubjectFromStudent(id_student, id_subjects);
    }


}





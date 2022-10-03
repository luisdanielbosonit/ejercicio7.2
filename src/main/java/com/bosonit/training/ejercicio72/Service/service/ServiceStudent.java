package com.bosonit.training.ejercicio72.Service.service;

import com.bosonit.training.ejercicio72.DTOS.INput.StudentINputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.StudentOUTputDTObySubject;
import com.bosonit.training.ejercicio72.DTOS.OUTput.StudentOUTputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.StudentSubjectOUTputDto;

import java.util.List;

public interface ServiceStudent {

    public StudentOUTputDto viewIdStudent(String id_student)throws Exception;

    public StudentOUTputDto loadStudent(StudentINputDto studentINputDto) throws Exception;

    public StudentOUTputDto updateStudent(String id_student,StudentINputDto studentINputDto) throws Exception;

    public void deleteStudent(String id_student) throws Exception;

    public List<StudentOUTputDto> viewAllStudent();

    public StudentOUTputDTObySubject studenbysubject(String id_student, List<StudentSubjectOUTputDto> subjectsOutputDto);

    public String addSubjectToStudent(String id_student, List<String> subjectsIds);

    public String deleteSubjectFromStudent(String id_student, List<String> subjectsIds);


}

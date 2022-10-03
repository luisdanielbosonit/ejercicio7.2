package com.bosonit.training.ejercicio72.Service.service;

import com.bosonit.training.ejercicio72.DTOS.INput.StudentSubjectINputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.StudentSubjectOUTputDto;

import java.util.List;

public interface ServiceStudentSubject {

    public StudentSubjectOUTputDto viewIdStudentSubject(String id_subject)throws Exception;

    public StudentSubjectOUTputDto loadStudentSubject(StudentSubjectINputDto studentSubjectINputDto);

    public StudentSubjectOUTputDto updateStudentSubject(String id_subject,StudentSubjectINputDto studentSubjectINputDto) throws Exception;

    public void deleteStudentSubject(String id_subject);

    public List<StudentSubjectOUTputDto> viewAllStudentSubject();

    public List<StudentSubjectOUTputDto> subjectbyId(String id_student);




}

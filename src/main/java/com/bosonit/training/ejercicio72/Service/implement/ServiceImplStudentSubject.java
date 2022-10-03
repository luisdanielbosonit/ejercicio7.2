package com.bosonit.training.ejercicio72.Service.implement;

import com.bosonit.training.ejercicio72.DTOS.INput.StudentSubjectINputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.StudentSubjectOUTputDto;
import com.bosonit.training.ejercicio72.Entity.Student;
import com.bosonit.training.ejercicio72.Entity.StudentSubject;
import com.bosonit.training.ejercicio72.Entity.Teacher;
import com.bosonit.training.ejercicio72.Exception.EntityNotFoundException;
import com.bosonit.training.ejercicio72.Repository.PersonaDao;
import com.bosonit.training.ejercicio72.Repository.StudentDao;
import com.bosonit.training.ejercicio72.Repository.StudentSubjectDao;
import com.bosonit.training.ejercicio72.Repository.TeacherDao;
import com.bosonit.training.ejercicio72.Service.service.ServiceStudentSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class ServiceImplStudentSubject implements ServiceStudentSubject {

    @Autowired
    StudentSubjectDao studentSubjectDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    PersonaDao personaDao;

    @Autowired
    TeacherDao teacherDao;


    @Override
    public StudentSubjectOUTputDto viewIdStudentSubject(String id_subject) throws Exception {
        StudentSubject studentSubject = studentSubjectDao.findById(id_subject).
                orElseThrow(() -> {
                    throw new EntityNotFoundException("The person with Id: " + id_subject + " does not exist in the database", HttpStatus.FOUND, new Date());
                });
        return new StudentSubjectOUTputDto(studentSubject);
    }

    @Override
    public StudentSubjectOUTputDto loadStudentSubject(StudentSubjectINputDto studentSubjectINputDto) {

        StudentSubject studentSubject = new StudentSubject(studentSubjectINputDto);

        if (studentSubjectINputDto.getId_teacher() != null) {
            studentSubject.setTeacher(getTeacher(studentSubjectINputDto.getId_teacher()));
        }
        List<Student> students = getStudentsIds(studentSubjectINputDto.getId_student());
        studentSubject.setStudents(students);
        studentSubjectDao.save(studentSubject);
        return new StudentSubjectOUTputDto(studentSubject);

    }

    @Override
    public StudentSubjectOUTputDto updateStudentSubject(String id_subject, StudentSubjectINputDto studentSubjectINputDto) throws Exception {
//        studentSubjectDao.save(studentSubjectINputDto);
//        Optional<StudentSubject> studentSubjectOptional = studentSubjectDao.findById(id_subject);
//
//        if (studentSubjectOptional.isEmpty())
//            throw new EntityNotFoundException("Subject no exist",HttpStatus.FOUND, new Date());
//        return studentSubjectDao.save(studentSubjectINputDto);
        return null;
    }

    @Override
    public void deleteStudentSubject(String id_subject) {
        studentSubjectDao.deleteById(id_subject);

    }

    @Override
    public List<StudentSubjectOUTputDto> viewAllStudentSubject() {
        List<StudentSubjectOUTputDto> listSubject = new ArrayList<>();
        studentSubjectDao.findAll().forEach(studentSubject -> {
            StudentSubjectOUTputDto studentSubjectOUTputDto = new StudentSubjectOUTputDto(studentSubject);
            listSubject.add(studentSubjectOUTputDto);
        });

        return listSubject;
    }

    @Override
    public List<StudentSubjectOUTputDto> subjectbyId(String id_student) {
        Student student = studentDao.findById(id_student)
                .orElseThrow(() -> new EntityNotFoundException("The Student with Id: " + id_student + " does not exist in the database", HttpStatus.FOUND, new Date()));

        return student.getStudentSubjects().stream().map(StudentSubjectOUTputDto::new).collect(Collectors.toList());
    }

    private List<Student> getStudentsIds(List<String> ids) {
        List<Student> students = new ArrayList<>();
        if (ids != null) {
            students = studentDao.findAllById(ids);
            if (ids.size() != students.size()) {
                throw new EntityNotFoundException("The Student with Id: " + ids + " does not exist in the database", HttpStatus.FOUND, new Date());
            }
            ;
        }
        return students;
    }
    private Teacher getTeacher(String id) {
        return teacherDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The Student with Id: " + id + " does not exist in the database", HttpStatus.FOUND, new Date()));
    }
}





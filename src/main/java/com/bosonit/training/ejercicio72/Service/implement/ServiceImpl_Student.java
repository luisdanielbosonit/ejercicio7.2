package com.bosonit.training.ejercicio72.Service.implement;

import com.bosonit.training.ejercicio72.DTOS.INput.StudentINputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.StudentOUTputDTObySubject;
import com.bosonit.training.ejercicio72.DTOS.OUTput.StudentOUTputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.StudentSubjectOUTputDto;
import com.bosonit.training.ejercicio72.Entity.Persona;
import com.bosonit.training.ejercicio72.Entity.Student;
import com.bosonit.training.ejercicio72.Entity.StudentSubject;
import com.bosonit.training.ejercicio72.Entity.Teacher;
import com.bosonit.training.ejercicio72.Exception.EntityNotFoundException;
import com.bosonit.training.ejercicio72.Exception.UnprocessableEntityException;
import com.bosonit.training.ejercicio72.Repository.PersonaDao;
import com.bosonit.training.ejercicio72.Repository.StudentDao;
import com.bosonit.training.ejercicio72.Repository.StudentSubjectDao;
import com.bosonit.training.ejercicio72.Repository.TeacherDao;
import com.bosonit.training.ejercicio72.Service.service.ServiceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Service
public class ServiceImpl_Student implements ServiceStudent {

    @Autowired
    StudentDao studentDao;

    @Autowired
    PersonaDao personaDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentSubjectDao studentSubjectDao;


    @Override
    public StudentOUTputDto viewIdStudent(String id_student) throws Exception {
        Student student= studentDao.findById(id_student).
                orElseThrow(()->{throw new EntityNotFoundException("The person with Id: "+id_student+" does not exist in the database", HttpStatus.FOUND,new Date());});
      return new StudentOUTputDto(student);
    }

    @Override
    public StudentOUTputDto loadStudent(StudentINputDto studentINputDto) throws Exception{
        Persona persona= personaDao.findById(studentINputDto.getId_persona()).orElseThrow(()->
        {throw new EntityNotFoundException("The person with Id: "+ studentINputDto.getId_student()+" does not exist in the database", HttpStatus.FOUND,new Date());});

        Teacher teacher= teacherDao.getReferenceById(studentINputDto.getId_teacher());
        if(teacherDao.getReferenceById(studentINputDto.getId_teacher())==null){
            throw new EntityNotFoundException("The person with Id: "+ studentINputDto.getId_teacher()+" does not exist in the database", HttpStatus.FOUND,new Date());};

        Student student= studentINputDto.tranfomINputStuden(persona,teacher);

        studentDao.save(student);

        return new StudentOUTputDto(student);
        }

    @Override
    public StudentOUTputDto updateStudent(String id_student, StudentINputDto studentINputDto) throws Exception {
        Student student= studentDao.findById(id_student).orElseThrow(()->
        {throw new EntityNotFoundException("The person with Id: "+ id_student+" does not exist in the database", HttpStatus.FOUND,new Date());});

        Persona persona= personaDao.findById(studentINputDto.getId_persona()).orElseThrow(()->
        {throw new EntityNotFoundException("The person with Id: "+ studentINputDto.getId_persona()+" does not exist in the database", HttpStatus.FOUND,new Date());});
        student.update(studentINputDto);
        student.setPersona(persona);
        studentDao.save(student);
        return new StudentOUTputDto(student);
    }

    @Override
    public void deleteStudent(String id_student) throws Exception {
        Student student = studentDao.findById(id_student).orElseThrow(()-> {throw new EntityNotFoundException("The person with Id: "+id_student+" does not exist in the database", HttpStatus.FOUND,new Date());});
        studentDao.delete(student);
    }

    @Override
    public List<StudentOUTputDto> viewAllStudent() {
        List<StudentOUTputDto> listaStudent=new ArrayList<>();
        studentDao.findAll().forEach(student -> {
            StudentOUTputDto studentOUTputDto = new StudentOUTputDto(student);
            listaStudent.add(studentOUTputDto);
        });
        return listaStudent;
    }

    @Override
    public StudentOUTputDTObySubject studenbysubject(String id_student, List<StudentSubjectOUTputDto> subjectsOutputDto) {
        Student student= studentDao.findById(id_student).orElseThrow(() -> {throw new EntityNotFoundException("The person with Id: "+id_student+" does not exist in the database", HttpStatus.FOUND,new Date());});
        List<StudentSubject> studentSubjects=studentSubjectDao.findAll().stream().toList();
        student.getStudentSubjects();
        studentDao.save(student);
        return new StudentOUTputDTObySubject(student);
    }

    @Override
    @Transactional
    public String addSubjectToStudent(String id_student, List<String> id_subjects) {

        Student student = studentDao.findById(id_student).orElseThrow(() -> {throw new EntityNotFoundException("The person with Id: "+id_student+" does not exist in the database", HttpStatus.FOUND,new Date());});

        StudentSubject studentSubject = studentSubjectDao.findById(id_subjects.toString()).orElseThrow(() -> {throw new EntityNotFoundException("The person with Id: "+id_subjects+" does not exist in the database", HttpStatus.FOUND,new Date());});
        if(studentSubject.getStudents().contains(student)) throw new UnprocessableEntityException("The person with Id: "+id_subjects+" does not exist in the database", HttpStatus.FOUND,new Date());

            studentSubject.getStudents().add(student);

            studentSubjectDao.save(studentSubject);

        return "Subjects have been added";
    }

    @Override
    @Transactional
    public String deleteSubjectFromStudent(String id_student, List<String> id_subjects) {
        Student student = studentDao.findById(id_student).orElseThrow(() -> {throw new EntityNotFoundException("The person with Id: "+id_student+" does not exist in the database", HttpStatus.FOUND,new Date());});

        for(String subjectId : id_subjects){
            StudentSubject subject = studentSubjectDao.findById(subjectId).orElseThrow(() -> {throw new EntityNotFoundException("The person with Id: "+id_subjects+" does not exist in the database", HttpStatus.FOUND,new Date());});

            if(!subject.getStudents().contains(student))
            {throw new EntityNotFoundException("The person with Id: "+id_subjects+" does not exist in the database", HttpStatus.FOUND,new Date());};

            subject.getStudents().remove(student);

            studentSubjectDao.save(subject);
        }

        return "Subjects have been deleted";
    }


    }






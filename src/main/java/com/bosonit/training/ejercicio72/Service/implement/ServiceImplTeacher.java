package com.bosonit.training.ejercicio72.Service.implement;

import com.bosonit.training.ejercicio72.DTOS.INput.TeacherINputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.TeacherOUTpuDto;
import com.bosonit.training.ejercicio72.Entity.Persona;
import com.bosonit.training.ejercicio72.Entity.Teacher;
import com.bosonit.training.ejercicio72.Exception.EntityNotFoundException;
import com.bosonit.training.ejercicio72.Repository.PersonaDao;
import com.bosonit.training.ejercicio72.Repository.TeacherDao;
import com.bosonit.training.ejercicio72.Service.service.ServiceTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ServiceImplTeacher implements ServiceTeacher {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    PersonaDao personaDao;

    @Override
    public TeacherOUTpuDto viewIdTeacher(String id_teacher) {
        Teacher teacher= teacherDao.findById(id_teacher).orElseThrow(()->{throw new EntityNotFoundException("The person with Id: "+id_teacher+" does not exist in the database", HttpStatus.FOUND,new Date());});
        return new TeacherOUTpuDto(teacher);
    }

    @Override
    public TeacherOUTpuDto loadTeacher(TeacherINputDto teacherINputDto) {
        Persona persona= personaDao.findById(teacherINputDto.getId_persona()).orElseThrow(()->{throw new EntityNotFoundException("The person with Id: "+ teacherINputDto.getId_persona()+" does not exist in the database", HttpStatus.FOUND,new Date());});
        Teacher teacher=new Teacher();
        teacher.setPersona(persona);
        teacher.setComents(teacherINputDto.getComents());
        teacher.setBranch(teacherINputDto.getBranch());

        teacherDao.save(teacher);
        return new  TeacherOUTpuDto(teacher);
    }

    @Override
    public TeacherOUTpuDto updateTeacher(String id_teacher, TeacherINputDto teacherINputDto)  {
        Teacher teacher=teacherDao.findById(id_teacher).orElseThrow(()->
        {throw new EntityNotFoundException("The person with Id: "+ id_teacher+" does not exist in the database", HttpStatus.FOUND,new Date());});

        Persona persona= personaDao.findById(teacherINputDto.getId_persona()).orElseThrow(()->
        {throw new EntityNotFoundException("The person with Id: "+ teacherINputDto.getId_persona()+" does not exist in the database", HttpStatus.FOUND,new Date());});

        teacher.setBranch(teacherINputDto.getBranch());
        teacher.setComents(teacherINputDto.getComents());
        teacher.setPersona(persona);

        teacherDao.save(teacher);

        return new TeacherOUTpuDto(teacher);

    }

    @Override
    public void deleteTeacher(String id_teacher) throws Exception {
        Teacher teacher= teacherDao.findById(id_teacher).orElseThrow(()-> {throw new EntityNotFoundException("The person with Id: "+id_teacher+" does not exist in the database", HttpStatus.FOUND,new Date());});
        teacherDao.delete(teacher);

    }

    @Override
    public List<TeacherOUTpuDto> viewAllTeacher() {
        List<TeacherOUTpuDto> listateacher=new ArrayList<>();
        teacherDao.findAll().forEach(teacher -> {
            TeacherOUTpuDto studentOUTputDto = new TeacherOUTpuDto(teacher);
            listateacher.add(studentOUTputDto);
        });
        return listateacher;
    }
}

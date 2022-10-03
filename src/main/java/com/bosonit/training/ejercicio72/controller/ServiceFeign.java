package com.bosonit.training.ejercicio72.controller;

import com.bosonit.training.ejercicio72.DTOS.OUTput.TeacherOUTpuDto;
import com.bosonit.training.ejercicio72.Exception.EntityNotFoundException;
import com.bosonit.training.ejercicio72.Repository.TeacherDao;
import com.bosonit.training.ejercicio72.Service.service.ServiceTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServiceFeign implements Teacherfeign {
    @Autowired
    ServiceTeacher serviceTeacher;

    @Autowired
    TeacherDao teacherDao;

    @Override
    public TeacherOUTpuDto getTeacher(String id_teacher) throws Exception {
        return teacherDao.findById(id_teacher).map(TeacherOUTpuDto::new).
                orElseThrow(() -> {
                    throw new EntityNotFoundException("The person with Id: " + id_teacher + " does not exist in the database", HttpStatus.FOUND, new Date());
                });
    }
}





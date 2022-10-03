package com.bosonit.training.ejercicio72.Service.service;

import com.bosonit.training.ejercicio72.DTOS.INput.TeacherINputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.TeacherOUTpuDto;

import java.util.List;

public interface ServiceTeacher {
    public TeacherOUTpuDto viewIdTeacher(String id_teacher)throws Exception;

    public TeacherOUTpuDto loadTeacher(TeacherINputDto teacherINputDto);

    public TeacherOUTpuDto updateTeacher(String id_teacher,TeacherINputDto teacherINputDto) throws Exception;

    public void deleteTeacher(String id_teacher) throws Exception;

    public List<TeacherOUTpuDto> viewAllTeacher();
}

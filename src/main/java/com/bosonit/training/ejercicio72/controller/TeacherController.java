package com.bosonit.training.ejercicio72.controller;

import com.bosonit.training.ejercicio72.DTOS.INput.TeacherINputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.TeacherOUTpuDto;
import com.bosonit.training.ejercicio72.Service.implement.ServiceImplTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    ServiceImplTeacher servicetacher;

    @PostMapping("/upload")
    public void loadstudent(@RequestBody TeacherINputDto teacherINputDto){
        servicetacher.loadTeacher(teacherINputDto);
    }

    @GetMapping("/{id_teacher}")
    public TeacherOUTpuDto viewIdteacher(@PathVariable String id_teacher, @RequestParam(required = false) String newview) throws Exception {
        TeacherOUTpuDto teacher= servicetacher.viewIdTeacher(id_teacher);
        if (newview==null)
            newview="simple";

        if(newview.equals("full"))
            return teacher;


        //return teacher;
        return servicetacher.viewIdTeacher(id_teacher);
    }

    @GetMapping("teacher/{id_teacher}")
    public TeacherOUTpuDto viewidteacherbyId(@PathVariable String id_teacher) throws Exception {
        return servicetacher.viewIdTeacher(id_teacher);
    }

    @PutMapping("/{id_teacher}")
    public TeacherOUTpuDto updateteacher(@PathVariable String id_teacher, @RequestBody TeacherINputDto teacherINputDto) throws Exception{
        TeacherOUTpuDto modifiedteacher= servicetacher.viewIdTeacher(id_teacher);
//        //serviceStuden.updateTeacher(id_teacher,teacher);
        return servicetacher.updateTeacher(id_teacher,teacherINputDto);


    }

    @DeleteMapping("/delete/{id_teacher}")
    public TeacherOUTpuDto deleteteacher(@PathVariable String id_teacher) throws Exception {
        TeacherOUTpuDto teacherdelete = servicetacher.viewIdTeacher(id_teacher);
        servicetacher.deleteTeacher(id_teacher);
        return teacherdelete;
    }

    @GetMapping("/todos")
    public List<TeacherOUTpuDto> viewall(){
        return (List<TeacherOUTpuDto>) servicetacher.viewAllTeacher();
    }



}

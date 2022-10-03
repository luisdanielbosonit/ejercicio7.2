package com.bosonit.training.ejercicio72.controller;

import com.bosonit.training.ejercicio72.DTOS.OUTput.TeacherOUTpuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher")
public class FeignController {

    @Autowired
    ServiceFeign serviceFeign;

    @GetMapping("/feign/{id_teacher}")
    TeacherOUTpuDto getTeacher(@PathVariable String id_teacher)throws Exception{
        return serviceFeign.getTeacher(id_teacher);
    }
}

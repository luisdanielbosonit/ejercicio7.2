package com.bosonit.training.ejercicio72.controller;

import com.bosonit.training.ejercicio72.DTOS.OUTput.TeacherOUTpuDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "myFeign", url = "http://localhost:8081")
public interface Teacherfeign {

    @GetMapping("/feign/{id_teacher}")
    TeacherOUTpuDto getTeacher(@PathVariable("id_teacher") String id_teacher) throws Exception;
}

package com.bosonit.training.ejercicio72.DTOS.INput;

import com.bosonit.training.ejercicio72.Entity.StudentSubject;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link StudentSubject} entity
 */
@Data
public class StudentSubjectINputDto {

    private String id_teacher;
    private String nameSubject;
    private Date created_date;
    private Date termination_date;
    private List<String> id_student=new ArrayList<>();



    }


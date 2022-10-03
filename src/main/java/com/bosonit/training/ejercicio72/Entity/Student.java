package com.bosonit.training.ejercicio72.Entity;

import com.bosonit.training.ejercicio72.DTOS.INput.StudentINputDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name= "student")
public class Student implements Serializable {


    @Id
    @GeneratedValue(generator = "student-seq")
    @GenericGenerator(name = "student-seq", strategy = "com.bosonit.training.ejercicio72.MiGenerator")
    @Column(name = "id_student")
    String id_student;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_persona")
    Persona persona;

    @Column(nullable = false)
    int num_hours_week;

    @Column(nullable = false)
    String coments;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @Column(nullable = false)
    String branch;

    @ManyToMany(fetch= FetchType.LAZY)
    private List<StudentSubject> studentSubjects;


    public void update(@NotNull StudentINputDto studentINputDto) {
        setNum_hours_week(studentINputDto.getNum_hours_week());
        setComents(studentINputDto.getComents());
        setBranch(studentINputDto.getComents());
    }

    public Student(@NotNull StudentINputDto studentINputDTO) {
        setNum_hours_week(studentINputDTO.getNum_hours_week());
        setComents(studentINputDTO.getComents());
        setBranch(studentINputDTO.getBranch());

    }
}


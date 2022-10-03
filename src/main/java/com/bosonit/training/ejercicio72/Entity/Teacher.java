package com.bosonit.training.ejercicio72.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name= "teacher")
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(generator = "teacher-seq")
    @GenericGenerator(name = "teacher-seq",strategy = "com.bosonit.training.ejercicio72.MiGenerator")
    @Column(name="id_teacher", nullable = false)
    String id_teacher;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    String coments;

    String branch;

    @OneToMany
    private List<StudentSubject> studentSubject;

    @OneToMany
    private List<Student> students;

}

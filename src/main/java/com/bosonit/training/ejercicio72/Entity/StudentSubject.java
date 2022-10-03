package com.bosonit.training.ejercicio72.Entity;

import com.bosonit.training.ejercicio72.DTOS.INput.StudentSubjectINputDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="studentSubject")
@NoArgsConstructor
public class StudentSubject {
    @Id
    @GeneratedValue(generator = "subject-seq")
    @GenericGenerator(name = "subject-seq",strategy = "com.bosonit.training.ejercicio72.MiGenerator")
    @Column(name="id_subject", nullable = false)
    public String id_subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @ManyToMany
    @JoinTable(name = "student_subject_students",
            joinColumns = @JoinColumn(name = "student_subject_id_subject"),
            inverseJoinColumns = @JoinColumn(name = "students_id_student"))
    private List<Student> students = new ArrayList<>();

    public String nameSubject;
    private Date created_date;
    private Date termination_date;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public StudentSubject(StudentSubjectINputDto studentSubjectINputDto) {

        setNameSubject(studentSubjectINputDto.getNameSubject());
        setCreated_date(studentSubjectINputDto.getCreated_date());
        setTermination_date(studentSubjectINputDto.getTermination_date());
    }


}
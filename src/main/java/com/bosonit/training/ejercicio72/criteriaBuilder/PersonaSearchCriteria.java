package com.bosonit.training.ejercicio72.criteriaBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class PersonaSearchCriteria {
    private  String username;
    private  String name;
    private  String surname;
    private  Date created_date;
    private  String dateCriteria;
}

package com.bosonit.training.ejercicio72.criteriaBuilder;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PersonaPage {

    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "name";

}

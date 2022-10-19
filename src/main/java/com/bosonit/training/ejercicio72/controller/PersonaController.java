package com.bosonit.training.ejercicio72.controller;

import com.bosonit.training.ejercicio72.DTOS.INput.PersonaINputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.PersonaOUTputDto;
import com.bosonit.training.ejercicio72.Service.service.ServicePersona;
import com.bosonit.training.ejercicio72.criteriaBuilder.PersonaPage;
import com.bosonit.training.ejercicio72.criteriaBuilder.PersonaSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/persona")
@CrossOrigin
public class PersonaController {

    @Autowired
    ServicePersona servicepersona;


    @PostMapping("/addperson")
    public PersonaOUTputDto guardarpersona(@RequestBody PersonaINputDto personaINputDto) throws Exception {
        return servicepersona.loadpersona(personaINputDto);
    }

    @GetMapping("get/{id}")
    public PersonaOUTputDto show(@PathVariable Integer id) throws Exception {

        return servicepersona.viewId(id);

    }
    @GetMapping("/view/{name}")
    public List<PersonaOUTputDto> getPersonByUsername(@PathVariable String name) throws Exception {
        return servicepersona.viewPerson(name);
    }


    @PutMapping("/put/{id}")
    public PersonaOUTputDto modificarpersona(@PathVariable Integer id,@RequestBody PersonaINputDto personaINputDto) throws Exception{

        return servicepersona.update(id,personaINputDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deletepersona(@PathVariable Integer id) throws Exception {
        servicepersona.delete(id);
        }

    @GetMapping("/getall")
    public List<PersonaOUTputDto> viewall(){
        return (List<PersonaOUTputDto>) servicepersona.viewall();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PersonaOUTputDto>> getSearch(PersonaPage personaPage, PersonaSearchCriteria personaSearchCriteria,
                                                           @RequestParam(required = false) String username,
                                                           @RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String surname,
                                                           @RequestParam(required = false) Date created_Date,
                                                           @RequestParam(required = false) String dateCriteria,
                                                           @RequestParam(required = false) String sortBy,
                                                           @RequestParam (required = false) Integer page) throws Exception {



//        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(created_Date);


        return  new ResponseEntity<>(servicepersona.personaCriterial(personaPage, personaSearchCriteria), HttpStatus.OK);
    }
}


package com.bosonit.training.ejercicio72.Service.service;

import com.bosonit.training.ejercicio72.DTOS.INput.PersonaINputDto;
import com.bosonit.training.ejercicio72.DTOS.OUTput.PersonaOUTputDto;

import java.util.List;

public interface ServicePersona {

    public PersonaOUTputDto viewId(Integer id) throws Exception; //ok

    public List<PersonaOUTputDto> viewPerson(String name) throws Exception ; //ok

    PersonaOUTputDto update(Integer id, PersonaINputDto personaINputDto) throws Exception;//ok

    public void delete(Integer id) throws Exception; //ok

    public List<PersonaOUTputDto> viewall();

    public PersonaOUTputDto loadpersona(PersonaINputDto personaINputDto)throws Exception; //ok

}

package com.bosonit.training.ejercicio72.criteriaBuilder;

import com.bosonit.training.ejercicio72.Entity.Persona;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PersonaCriterialRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public PersonaCriterialRepository(EntityManager entityManager){
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Persona> findAllWiithFilters(PersonaPage personaPage, PersonaSearchCriteria personaSearchCriteria) {

        CriteriaQuery<Persona> criteriaQuery = criteriaBuilder.createQuery(Persona.class);
        Root<Persona> PersonaRoot = criteriaQuery.from(Persona.class);
        Predicate predicate = getPredicate(personaSearchCriteria, PersonaRoot);
        criteriaQuery.where(predicate);
        setOrder(personaPage, criteriaQuery,PersonaRoot);

        TypedQuery<Persona> typedQuery= entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(personaPage.getPageNumber()*personaPage.getPageSize());
        typedQuery.setMaxResults(personaPage.getPageSize());

        Pageable pageable= getPegeable(personaPage);

        long personaCount = getpersonaCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, personaCount);
    }

    private Predicate getPredicate(PersonaSearchCriteria personaSearchCriteria,
                                   Root<Persona> personaRoot) {
        List<Predicate> predicateList = new ArrayList<>();
        if (Objects.nonNull(personaSearchCriteria.getUsername())) {
            predicateList.add(criteriaBuilder.like(personaRoot.get("username"), "%" + personaSearchCriteria.getUsername() + "%")
            );
        }
        if (Objects.nonNull(personaSearchCriteria.getName())) {
            predicateList.add(criteriaBuilder.like(personaRoot.get("name"), "%" + personaSearchCriteria.getName() + "%")
            );
        }
        if (Objects.nonNull(personaSearchCriteria.getSurname())) {
            predicateList.add(criteriaBuilder.like(personaRoot.get("surname"), "%" + personaSearchCriteria.getSurname() + "%")
            );
        }
        if (Objects.nonNull(personaSearchCriteria.getCreated_date())) {

                if(personaSearchCriteria.getDateCriteria() != null) {
                    if (personaSearchCriteria.getDateCriteria().equals("prev"))
                        predicateList.add(criteriaBuilder.greaterThanOrEqualTo(personaRoot.get("created_date"), personaSearchCriteria.getCreated_date()));
                }
                else
                    predicateList.add(criteriaBuilder.lessThan(personaRoot.get("created_date"), personaSearchCriteria.getCreated_date()));
        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }
    private void setOrder(PersonaPage personaPage, CriteriaQuery<Persona> criteriaQuery, Root<Persona> personaRoot) {
        if (personaPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(personaRoot.get(personaPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(personaRoot.get(personaPage.getSortBy())));
        }
    }
    private Pageable getPegeable(PersonaPage personaPage) {
        Sort sort= Sort.by(personaPage.getSortDirection(), personaPage.getSortBy());
        return PageRequest.of(personaPage.getPageNumber(),personaPage.getPageSize(), sort);
        }
    private long getpersonaCount(Predicate predicate){
        CriteriaQuery<Long> countQuery=criteriaBuilder.createQuery(Long.class);
        Root<Persona> countRoot= countQuery.from(Persona.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();
}
}

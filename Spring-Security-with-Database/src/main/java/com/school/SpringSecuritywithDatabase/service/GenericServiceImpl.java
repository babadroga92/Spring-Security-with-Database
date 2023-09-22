package com.school.SpringSecuritywithDatabase.service;

import com.school.SpringSecuritywithDatabase.exc.WrongIdException;
import com.school.SpringSecuritywithDatabase.model.generic.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl<E extends GenericEntity> implements GenericService<E> {

    private JpaRepository<E, Integer> jpaRepository;
    public GenericServiceImpl() {
    }
    public GenericServiceImpl(JpaRepository<E, Integer> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    @Override
    public E create(E entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public List<E> findAll(){
        return jpaRepository.findAll();
    }

    @Override
    public E getById(int id){
        Optional<E> optional = jpaRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new WrongIdException("Entity with this id doesn't exist. ");
        }
    }
    @Override
    public String delete(int id){
        E entity = getById(id);
        jpaRepository.delete(entity);
        return "User with id:" + id + " has been deleted. ";
    }
}

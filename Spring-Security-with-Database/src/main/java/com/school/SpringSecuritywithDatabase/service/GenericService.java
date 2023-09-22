package com.school.SpringSecuritywithDatabase.service;

import java.util.List;

public interface GenericService<E> {
    List<E> findAll();
    E getById(int id);
    E create(E entity);

    String delete(int id);

}

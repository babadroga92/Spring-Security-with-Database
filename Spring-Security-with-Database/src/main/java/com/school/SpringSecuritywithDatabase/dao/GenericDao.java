package com.school.SpringSecuritywithDatabase.dao;

import com.school.SpringSecuritywithDatabase.model.generic.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericDao<T extends GenericEntity> extends JpaRepository<T, Integer> {
}

package com.babadroga.Task.Management.API.dao;

import com.babadroga.Task.Management.API.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskDao extends JpaRepository<Task, Long> {

}

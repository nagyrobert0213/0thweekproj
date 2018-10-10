package com.greenfoxacademy.zerothweekproject.repositories;

import com.greenfoxacademy.zerothweekproject.models.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository <Todo, Long>{
    List<Todo> findByTitleContains(String title);
}

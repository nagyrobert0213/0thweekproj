package com.greenfoxacademy.zerothweekproject.services;

import com.greenfoxacademy.zerothweekproject.models.Todo;

public interface TodoService {
    void editPost(Long id, String title, boolean done, boolean urgent);
    void todoSave(String title);
    Todo findById(Long id);
    void deleteById(Long id);
}

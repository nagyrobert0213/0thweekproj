package com.greenfoxacademy.zerothweekproject.services;

import com.greenfoxacademy.zerothweekproject.models.Todo;
import com.greenfoxacademy.zerothweekproject.repositories.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void editPost(Long id, String title, boolean done, boolean urgent) {
        Todo todo = todoRepository.findById(id).get();
        todo.setDone(done);
        todo.setTitle(title);
        todo.setUrgent(urgent);
        todoRepository.save(todo);
    }

    @Override
    public void todoSave(String title) {
        todoRepository.save(new Todo(title));
    }

    @Override
    public Todo findById(Long id) {
        return todoRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }
}

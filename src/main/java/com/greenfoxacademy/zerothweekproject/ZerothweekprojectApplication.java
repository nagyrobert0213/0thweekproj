package com.greenfoxacademy.zerothweekproject;

import com.greenfoxacademy.zerothweekproject.models.Todo;
import com.greenfoxacademy.zerothweekproject.repositories.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ZerothweekprojectApplication implements CommandLineRunner {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private TodoRepository todoRepository;

    public ZerothweekprojectApplication(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ZerothweekprojectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Todo todo = new Todo();
        Todo todo1 = new Todo();
        Todo todo2 = new Todo();
        todo.setTitle("go to the gym");
        todo1.setTitle("drink protein");
        todo2.setTitle("drink water");
        todo.setDone(true);
        todoRepository.save(todo);
        todoRepository.save(todo1);
        todoRepository.save(todo2);
    }
}

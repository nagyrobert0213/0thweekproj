package com.greenfoxacademy.zerothweekproject.controllers;

import com.greenfoxacademy.zerothweekproject.models.Todo;
import com.greenfoxacademy.zerothweekproject.repositories.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    private TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping(value = {"/todo", "/"})
    public String list(Model model, @RequestParam(value = "isActive", required = false) String isActive) {
        boolean listActive = false;
        if (isActive != null && isActive.equals("true")) {
            listActive = true;
        }
        model.addAttribute("listActive", listActive);
        model.addAttribute("todoList", todoRepository.findAll());
        return "todoslist";
    }

    @GetMapping("/isActive")
    public String isActive(Model model) {
        model.addAttribute("todoListActive", todoRepository.findAll());
        return "todoedit";
    }

    @GetMapping("/search")
    public String isActive(Model model, String search) {
        model.addAttribute("todoList", todoRepository.findByTitleContains(search));

//            model.addAttribute("todoListSearch", todoRepository.findAll());
        return "todoslist";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        todoRepository.deleteById(id);
        return "redirect:/todo";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("id", todo.getId());
        model.addAttribute("title", todo.getTitle());
        model.addAttribute("urgent", todo.isUrgent());
        model.addAttribute("done", todo.isDone());
        return "todoedit";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable Long id, String title, boolean done, boolean urgent) {
        Todo todo = todoRepository.findById(id).get();
        todo.setDone(done);
        todo.setTitle(title);
        todo.setUrgent(urgent);
        todoRepository.save(todo);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addGet() {
        return "todoadd";
    }

    @PostMapping("/add")
    public String addPost(String title) {
        todoRepository.save(new Todo(title));
        return "redirect:/todo";
    }
}

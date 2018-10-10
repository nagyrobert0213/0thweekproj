package com.greenfoxacademy.zerothweekproject.controllers;

import com.greenfoxacademy.zerothweekproject.models.Todo;
import com.greenfoxacademy.zerothweekproject.repositories.TodoRepository;
import com.greenfoxacademy.zerothweekproject.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    private TodoRepository todoRepository;
    private TodoService todoService;

    public TodoController(TodoRepository todoRepository, TodoService todoService) {
        this.todoRepository = todoRepository;
        this.todoService = todoService;
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
        todoService.deleteById(id);
        return "redirect:/todo";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("id", todo.getId());
        model.addAttribute("title", todo.getTitle());
        model.addAttribute("urgent", todo.isUrgent());
        model.addAttribute("done", todo.isDone());
        return "todoedit";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable Long id, String title, boolean done, boolean urgent) {
        todoService.editPost(id,title,done,urgent);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addGet() {
        return "todoadd";
    }

    @PostMapping("/add")
    public String addPost(String title) {
        todoService.todoSave(title);
        return "redirect:/todo";
    }
}

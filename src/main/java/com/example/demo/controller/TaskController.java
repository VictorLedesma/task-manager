package com.example.demo.controller;

import com.example.demo.repository.TaskRepository;
import com.example.demo.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskRepository repo;

    public TaskController(TaskRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List <Task> list() {
        return repo.findAll();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return repo.save(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task taskData) {
        Task task = repo.findById(id).orElseThrow();
        task.setTitle(taskData.getTitle());
        task.setDescription(taskData.getDescription());
        task.setCompleted(taskData.isCompleted());
        return repo.save(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repo.deleteById(id);
    }

}

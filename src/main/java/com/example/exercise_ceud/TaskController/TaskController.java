package com.example.exercise_ceud.TaskController;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public List<Task> getAllTasks() {
        return tasks;
    }

    @PostMapping("/add")
    public String createTask(@RequestBody Task task) {
        tasks.add(task);
        return "Task created";
    }

    @PutMapping("/update/{index}")
    public String updateTask(@PathVariable int index, @RequestBody Task task) {

            tasks.set(index, task);
            return "Task updated";
        }

    @DeleteMapping("/{index}")
    public void deleteTask(@PathVariable int index) {
            tasks.remove(index);
        }

    @PatchMapping("/status/{index}")
    public String changeTaskStatus(@PathVariable int index, @RequestParam boolean status) {

            Task task = tasks.get(index);
            task.status = status;
            return "Task status changed";
    }

    @GetMapping("/search")
    public List<Task> searchTasksByTitle(@RequestParam String title) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.title.equals(title)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
}
}
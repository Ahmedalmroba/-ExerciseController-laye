package com.example.exercise_ceud.Controller;
import com.example.exercise_ceud.Api.Api;
import com.example.exercise_ceud.Task.Task;
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
    public Api createTask(@RequestBody Task task) {
        tasks.add(task);
        return new Api("Task created");
    }

    @PutMapping("/update/{index}")
    public String updateTask(@PathVariable int index, @RequestBody Task task) {

            tasks.set(index, task);
            return "Task updated";
        }

    @DeleteMapping("delete/{index}")
    public void deleteTask(@PathVariable int index) {
            tasks.remove(index);
        }

    @PutMapping("/status/{index}")
    public Api changeTaskStatus(@PathVariable int index, @RequestParam boolean status) {

            Task task = tasks.get(index);
            if (task.getStatus().equalsIgnoreCase("Not Done"))
            { task.setStatus("done")
                    ;}
            else return new Api("task already done " );
            return new Api("Task status changed");
    }

    @GetMapping("/search")
    public List<Task> searchTasksByTitle(@RequestParam String title) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
}
}
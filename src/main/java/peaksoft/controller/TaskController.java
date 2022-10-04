package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.service.LessonService;
import peaksoft.service.TaskService;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final LessonService lessonService;

    @Autowired
    public TaskController(TaskService taskService, LessonService lessonService) {
        this.taskService = taskService;
        this.lessonService = lessonService;
    }

    @GetMapping("/newTask/{courseIdTask}")
    public String addTask(Model model, @PathVariable("courseIdTask")Long courseIdTask){
        model.addAttribute("courseIdTask",courseIdTask);
        model.addAttribute("newTask",new Task());
        return "/tasks/newTask";
    }

    @PostMapping("/saveTask/{courseIdTask}")
    public String saveTask(@ModelAttribute("newTask")Task task,@PathVariable("courseIdTask")Long courseIdTask){
        Lesson lesson = lessonService.getLessonById(courseIdTask);
        taskService.saveTaskByLessonById(courseIdTask,task);
        return "redirect:/allInLesson/"+courseIdTask;
    }

    @GetMapping("/editTask/{id}")
    public String editTask(Model model,@PathVariable("id")Long id){
        Task task = taskService.getTaskById(id);
        model.addAttribute("task",task);
        model.addAttribute("lesson",task.getLesson().getId());
        return "/tasks/updateTask";
    }

    @PatchMapping("/updateTask/{id}")
    public String updateTask(@ModelAttribute("task")Task task,
                             @PathVariable("id")Long id){
        taskService.updateTaskById(id, task);
        return "lessons/innerLessonPage";
    }

    @DeleteMapping("/deleteTask/{deleteId}/{id}")
    public String deleteTask(@PathVariable("deleteId")Long deleteTask,
                             @PathVariable("id")Long id){
        taskService.deleteTask(deleteTask);
        return "redirect:/allInCourses/"+id;
    }

}

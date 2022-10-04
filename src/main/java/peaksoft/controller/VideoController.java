package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.entity.Video;
import peaksoft.service.LessonService;
import peaksoft.service.VideoService;

@Controller
@RequestMapping
public class VideoController {

    private final VideoService videoService;
    private final LessonService lessonService;
    @Autowired
    public VideoController(VideoService videoService, LessonService lessonService) {
        this.videoService = videoService;
        this.lessonService = lessonService;
    }

    @GetMapping("/newVideo/{lessonIdVideo}")
    public String addTask(Model model, @PathVariable("lessonIdVideo")Long lessonIdVideo){
        model.addAttribute("lessonIdVideo",lessonIdVideo);
        model.addAttribute("newVideo",new Video());
        return "/videos/newVideo";
    }

    @PostMapping("/saveVideo/{lessonIdVideo}")
    public String saveTask(@ModelAttribute("newVideo")Video video, @PathVariable("lessonIdVideo")Long lessonIdVideo){
        Lesson lesson = lessonService.getLessonById(lessonIdVideo);
        videoService.saveVideoByLessonId(lessonIdVideo,video);
        return "redirect:/allInLesson/"+lessonIdVideo;
    }
//
//    @GetMapping("/editVideo/{id}")
//    public String editTask(Model model,@PathVariable("id")Long id){
//        Task task = taskService.getTaskById(id);
//        model.addAttribute("task",task);
//        model.addAttribute("lesson",task.getLesson().getId());
//        return "/tasks/updateTask";
//    }
//
//    @PatchMapping("/updateTask/{id}")
//    public String updateTask(@ModelAttribute("task")Task task,
//                             @PathVariable("id")Long id){
//        taskService.updateTaskById(id, task);
//        return "lessons/innerLessonPage";
//    }
//
//    @DeleteMapping("/deleteTask/{deleteId}/{id}")
//    public String deleteTask(@PathVariable("deleteId")Long deleteTask,
//                             @PathVariable("id")Long id){
//        taskService.deleteTask(deleteTask);
//        return "redirect:/allInCourses/"+id;
//    }

}

package peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Lesson;
import peaksoft.service.CourseService;
import peaksoft.service.LessonService;
import peaksoft.service.TaskService;

@Controller
@RequestMapping
public class LessonController {

    private final LessonService lessonService;
    private final CourseService courseService;
    private final TaskService taskService;


    public LessonController(LessonService lessonService, CourseService courseService, TaskService taskService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
        this.taskService = taskService;
    }

    @GetMapping("/newLesson/{courseId}")
    public String addLesson(Model model, @PathVariable("courseId")Long id){
        model.addAttribute("courseId",id);
        model.addAttribute("newLesson",new Lesson());
        return "/lessons/newLesson";
    }

    @PostMapping("/saveLesson/{courseId}")
    public String saveLesson(@ModelAttribute("newLesson")Lesson lesson,@PathVariable("courseId")Long id){
//        Course course = courseService.getCourseById(id);
//        course.addLesson(lesson);
        lessonService.saveLessonByCourseId(id,lesson);
        return "redirect:/allInCourses/"+id;
    }

    @GetMapping("/editLesson/{lessonId}")
    public String editLesson(Model model,@PathVariable("lessonId")Long lessonId){
        Lesson lesson = lessonService.getLessonById(lessonId);
        model.addAttribute("lesson",lesson);
        model.addAttribute("course",lesson.getCourse().getId());
        return "/lessons/updateLesson";
    }

    @PatchMapping("/updateLesson/{lessonId}")
    public String updateLesson(@ModelAttribute("lesson")Lesson lesson,
                               @PathVariable("lessonId")Long lessonId){
        lessonService.updateLessonById(lessonId,lesson);
        return "redirect:/allInCourses/"+lessonId;
    }

    @DeleteMapping("/deleteLesson/{lessonId2}/{id}")
    public String deleteLesson(@PathVariable("lessonId2")Long lessonId,
                               @PathVariable("id")Long id){
        lessonService.deleteLesson(lessonId);
        return "redirect:/allInCourses/"+id;
    }

    @GetMapping("/allInLesson/{lessId}")
    public String getAllInLesson(@PathVariable("lessId")Long lessId,Model model){
        model.addAttribute("lessId",lessId);
        model.addAttribute("tasks",taskService.getAllTasksByLessonId(lessId));
        return "/lessons/innerLessonPage";
    }

}

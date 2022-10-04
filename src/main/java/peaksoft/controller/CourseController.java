package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;

import peaksoft.service.*;

@Controller
@RequestMapping
public class CourseController {

    private final CourseService courseService;
    private final InstructorService instructorService;
    private final StudentService studentService;
    private final LessonService lessonService;
    private final TaskService taskService;

    @Autowired
    public CourseController(CourseService courseService, InstructorService instructorService, StudentService studentService, LessonService lessonService, TaskService taskService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
        this.studentService = studentService;
        this.lessonService = lessonService;
        this.taskService = taskService;
    }

    @GetMapping("/allCourses/{id}")
    public String getAllCourses(@PathVariable("id") Long companyId, Model model) {
        model.addAttribute( "companyId", companyId);
        model.addAttribute("courses", courseService.getAllCoursesById(companyId));
//        model.addAttribute("instructor",instructorService.getInstructorById(companyId));
        model.addAttribute("instructors", instructorService.getAllInstructorsByCompanyId(companyId));
        model.addAttribute("student",studentService.getStudentById(companyId));
        model.addAttribute("students",studentService.getAllStudentByCompanyId(companyId));
        return "/companies/innerCompanyPage";
    }

    @GetMapping("/allInCourses/{courseId}")
    public String getAllInCourses(Model model,@PathVariable("courseId")Long courseId){
        model.addAttribute("lessons",lessonService.getAllLessonsByCourseId(courseId));
        return "/courses/innerCoursePage";
    }

    @GetMapping("/newCourse/{id}")
    public String addCourse(Model model, @PathVariable("id") Long id) {
        model.addAttribute("newCourse", new Course());
        model.addAttribute("id", id);
        return "/courses/newCoursePage";
    }

    @PostMapping("/{id}/saveCourse")
    public String saveCourse(@ModelAttribute("newCourse") Course course,
                             @PathVariable("id") Long companyId) {
        courseService.saveCourseByCompanyId(companyId,course);
        return "redirect:/allCourses/ " + companyId;
    }

    @GetMapping("/editCourse/{courId}")
    public String editCourse(Model model, @PathVariable("courId") Long id) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("company", course.getCompany().getId());
        return "/courses/updateCourse";
    }

    @PatchMapping("/updateCourse/{companyId}")
    public String updateCourse(@PathVariable("companyId") Long companyId,
                               @ModelAttribute("course") Course course) {
        courseService.updateCourseById(companyId, course);
        return "redirect:/allCourses/" + companyId;
    }

    @DeleteMapping("/deleteCourse/{courseId}/{compId}")
    public String deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourseById(courseId);

        return "redirect:/allCourses/{compId}";
    }
}

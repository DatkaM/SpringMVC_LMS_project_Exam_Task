package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;



@Controller
@RequestMapping
public class InstructorController {

    private final InstructorService instructorService;
    private final CompanyService companyService;
    private final CourseService courseService;

    @Autowired
    public InstructorController(InstructorService instructorService, CompanyService companyService, CourseService courseService) {
        this.instructorService = instructorService;
        this.companyService = companyService;
        this.courseService = courseService;
    }


//    @GetMapping("/allInstructors/{id}")
//    public String getAllInstructors(Model model, @PathVariable("id")Long companyId){
//        model.addAttribute("companyId",companyId);
//        model.addAttribute("instructors",instructorService.getAllInstructorsByCompanyId(companyId));
//        return "/companies/innerCompanyPage";
//    }

    @GetMapping("/newInstructor/{id}")
    public String addInstructor(Model model,@PathVariable("id")Long id){
        model.addAttribute("newInstructor",new Instructor());
        model.addAttribute("id",id);
        return "/instructors/newInstructor";
    }

    @PostMapping("/saveInstructor/{id}")
    public String saveInstructor(@ModelAttribute("newInstructor")Instructor instructor,
                                 @PathVariable("id")Long id){
        Company company = companyService.getCompanyById(id);
        instructor.setCompany(company);
        instructorService.saveInstructorByCompanyId(id,instructor);
        return "redirect:/allCourses/"+id;
    }

    @GetMapping("/editInstructor/{id}")
    public String editInstructor(Model model, @PathVariable("id")Long id){
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);
        model.addAttribute("company", instructor.getCompany().getId());
        return "/instructors/updateInstructor";
    }

    @PatchMapping("/updateInstructor/{id}")
    public String updateInstructor(@ModelAttribute("instructor") Instructor instructor,
                                   @PathVariable("id")Long id){
        Company company = companyService.getCompanyById(id);
        instructor.setCompany(company);
        instructorService.updateInstructor(id, instructor);
        return "redirect:/allCourses/" + id;
    }

    @DeleteMapping("/deleteInstructor/{compId2}/{id}")
    public String deleteInstructor(@PathVariable("id")Long id){
        instructorService.deleteInstructorById(id);
        return "redirect:/allCourses/{compId2}";
    }

    @GetMapping("/assignInstructor/{insId}{courseId}")
    public String assignMethod(Model model,@PathVariable("insId")Long id,
                               @PathVariable("courseId")Long courseId){
        model.addAttribute("assignInstructor",instructorService.getInstructorById(id));
        model.addAttribute("asCourse",courseService.getCourseById(courseId));

        return "/instructors/innerInstructorPage";
    }

    @PostMapping("/assignInsToCourse/{insId}/{courseId}/{id}")
    public String assignInstructorToCourse(@PathVariable("insId")Long insId,
                                           @PathVariable("courseId")Long courseId,
                                           @ModelAttribute("asCourse") Course course,
                                           @ModelAttribute("insId")Instructor instructor){

        instructorService.getInstructorById(insId);
        companyService.getCompanyById(courseId);
        instructorService.assignInstructorToCourse(insId,courseId);
        return "redirect:/allInstructors/{id}";
    }

}

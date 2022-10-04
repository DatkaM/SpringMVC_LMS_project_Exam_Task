package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Company;
import peaksoft.entity.Student;
import peaksoft.service.CompanyService;
import peaksoft.service.StudentService;

@Controller
@RequestMapping
public class StudentController {


    private final StudentService studentService;
    private final CompanyService companyService;

    @Autowired
    public StudentController(StudentService studentService, CompanyService companyService) {
        this.studentService = studentService;
        this.companyService = companyService;
    }

    @GetMapping("/newStudent/{justId}")
    public String addStudent(Model model, @PathVariable("justId")Long justId){
        model.addAttribute("newStudent",new Student());
        model.addAttribute("justId",justId);
        return "/students/newStudent";
    }

    @PostMapping("/saveStudent/{justId}")
    public String saveStudent(@ModelAttribute("newStudent")Student student,
                              @PathVariable("justId")Long justId){
        Company company = companyService.getCompanyById(justId);
        student.setCompany(company);
        studentService.saveStudentByCompanyId(justId,student);
        return "redirect:/allCourses/"+justId;
    }

    @GetMapping("/editStudent/{updateId}")
    public String editStudent(@PathVariable("updateId")Long updateId,Model model){
        Student student = studentService.getStudentById(updateId);
        model.addAttribute("student",student);
        model.addAttribute("company",student.getCompany().getId());
        return "/students/updateStudent";
    }

    @PatchMapping("/updateStudent/{updateId}")
    public String updateStudent(@PathVariable("updateId")Long updateId,
                                @ModelAttribute("student")Student student){
        studentService.updateStudentById(updateId,student);
        return "redirect:/allCourses/"+updateId;
    }

    @DeleteMapping("/deleteStudent/{studentId}/{id}")
    public String deleteStudent(@PathVariable("studentId")Long studentId,
                                @PathVariable("id")Long id){
        studentService.deleteStudentById(studentId);
        return "redirect:/allCourses/"+id;
    }
}

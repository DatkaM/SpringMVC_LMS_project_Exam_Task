package peaksoft.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Company;
import peaksoft.service.CompanyService;

@Controller
@RequestMapping
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/allCompanies")
    public String getAllCompanies(Model model){
        model.addAttribute("allCompany",companyService.getAllCompanies());
        return "/companies/mainCompanyPage";
    }

    @GetMapping("/new")
    public String addCompany(Model model){
        model.addAttribute("newCompany",new Company());
        return "/companies/newCompany";
    }

    @PostMapping("/save")
    public String saveCompany(@ModelAttribute("newCompany") Company company){
        companyService.saveCompany(company);
        return "redirect:/allCompanies";
    }

    @GetMapping("/edit/{id}")
    public String editCompany(Model model, @PathVariable("id")Long id){
        model.addAttribute("company",companyService.getCompanyById(id));
        return "/companies/updateCompany";
    }

    @PatchMapping("/update/{id}")
    public String updateCompany(@ModelAttribute("company")Company company){
        companyService.updateCompany(company);
        return "redirect:/allCompanies";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteCompanyById(@PathVariable("id") Long id) {
        companyService.deleteCompanyById(id);
        return "redirect:/allCompanies";
    }

}

package peaksoft.service;

import peaksoft.entity.Company;

import java.util.List;

public interface CompanyService {


    void saveCompany(Company company);

    void deleteCompanyById(Long id);

    Company getCompanyById(Long id);

    List<Company> getAllCompanies();

    void updateCompany(Company company);
}

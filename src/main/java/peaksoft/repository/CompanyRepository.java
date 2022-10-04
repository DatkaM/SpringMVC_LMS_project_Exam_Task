package peaksoft.repository;

import peaksoft.entity.Company;

import java.util.List;

public interface CompanyRepository {

    void saveCompany(Company company);

    void deleteCompanyById(Long id);

    Company getCompanyById(Long id);

    List<Company> getAllCompanies();

    void updateCompany(Company company);
}

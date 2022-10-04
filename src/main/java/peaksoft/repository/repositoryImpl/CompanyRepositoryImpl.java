package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Company;
import peaksoft.repository.CompanyRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void saveCompany(Company company) {
        manager.persist(company);
    }

    @Override
    public void deleteCompanyById(Long id) {
        Company company = manager.find(Company.class,id);
        manager.remove(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return manager.find(Company.class,id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return manager.createQuery("select c from Company c",Company.class).getResultList();
    }

    @Override
    public void updateCompany(Company company) {
        manager.merge(company);
    }
}

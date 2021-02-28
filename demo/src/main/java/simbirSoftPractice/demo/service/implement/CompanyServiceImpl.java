package simbirSoftPractice.demo.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Company;
import simbirSoftPractice.demo.dao.repository.CompanyRepository;
import simbirSoftPractice.demo.service.interfaces.CompanyService;


import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private final CompanyRepository companyRepo;

    public CompanyServiceImpl(CompanyRepository companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Override
    public List<Company> findAll() {
        return null;
    }

    @Override
    public Company getById(Long id) {
        return null;
    }

    @Override
    public void save(Company company) {

    }

    @Override
    public void deleteById(Long id) {

    }
}

package simbirSoftPractice.demo.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Company;
import simbirSoftPractice.demo.dao.repository.CompanyRepository;
import simbirSoftPractice.demo.dto.CompanyDto;
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
        return (List<Company>)companyRepo.findAll();
    }

    @Override
    public Company findById(Long id) {
        return companyRepo.findById(id).get();
    }

    @Override
    public void save(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        companyRepo.save(company);
    }

    @Override
    public void deleteById(Long id) {
        companyRepo.deleteById(id);
    }
}

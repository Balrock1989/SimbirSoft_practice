package simbirSoftPractice.demo.service.interfaces;

import simbirSoftPractice.demo.dao.entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company getById(Long id);

    void save(Company company);

    void deleteById(Long id);
}

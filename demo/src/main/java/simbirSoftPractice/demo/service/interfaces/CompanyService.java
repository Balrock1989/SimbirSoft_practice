package simbirSoftPractice.demo.service.interfaces;

import simbirSoftPractice.demo.dao.entity.Company;
import simbirSoftPractice.demo.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company findById(Long id);

    void save(CompanyDto companyDto);

    void deleteById(Long id);
}

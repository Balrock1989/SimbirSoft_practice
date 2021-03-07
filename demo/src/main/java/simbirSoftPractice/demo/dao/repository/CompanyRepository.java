package simbirSoftPractice.demo.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import simbirSoftPractice.demo.dao.entity.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company,Long> {
}

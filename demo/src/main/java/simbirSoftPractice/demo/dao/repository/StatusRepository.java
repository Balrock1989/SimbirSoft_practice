package simbirSoftPractice.demo.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import simbirSoftPractice.demo.dao.entity.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status,Long> {
}

package simbirSoftPractice.demo.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import simbirSoftPractice.demo.dao.entity.Compane;

@Repository
public interface CompaneRepository extends CrudRepository<Compane,Long> {
}

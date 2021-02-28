package simbirSoftPractice.demo.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import simbirSoftPractice.demo.dao.entity.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {
}

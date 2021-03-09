package simbirSoftPractice.demo.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import simbirSoftPractice.demo.dao.entity.Item;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {

    @Query("select i.id, i.name, i.price,i.quantity,s.name from Item as i inner join " +
            "Status as s on s.id = i.id where s.name = :title")
    List<Item> findAllBuyList(@Param("title") String title);
}

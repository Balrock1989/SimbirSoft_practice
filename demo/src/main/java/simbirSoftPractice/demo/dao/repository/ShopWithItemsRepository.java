package simbirSoftPractice.demo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simbirSoftPractice.demo.dao.entity.ShopWithItems;

@Repository
public interface ShopWithItemsRepository extends JpaRepository<ShopWithItems,Long> {
}

package simbirSoftPractice.demo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import simbirSoftPractice.demo.dao.entity.ShopWithItems;

import java.util.List;

@Repository
public interface ShopWithItemsRepository extends JpaRepository<ShopWithItems,Long> {

    @Query(value = "select s from ShopWithItems s where s.shopName in :name")
    List<ShopWithItems> findMiddleBuyInShop(@Param("name") String name);
}

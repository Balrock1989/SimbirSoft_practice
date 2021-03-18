package simbirSoftPractice.demo.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dto.ItemBuyDto;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {

    @Query(value = "select i from Item as i where i.status.name in :name")
    List<Item> findAllBuyItems(@Param("name")String name);

    @Query(value = "select i from Item as i where i.status.name in :statusName " +
            "and i.shopName in :shopName and i.dateTime " +
            "between :dateTimeStart and :dateTimeEnd")
    List<Item> findAllDebitedItemsInShop(@Param("statusName") String statusName,
                                         @Param("shopName")String shopName,
                                         @Param("dateTimeStart") LocalDateTime dateTimeStart,
                                         @Param("dateTimeEnd")LocalDateTime dateTimeEnd);

    @Query(value = "select i from Item as i where i.status.name in :statusName and i.shopName in :shopName")
    List<Item> findAllBuyItemsInShop(@Param("statusName")String statusName,
                                     @Param("shopName")String shopName);

    @Query(value = "select i from Item as i where i.shopName in :shopName ")
    List<Item> findByShopName(@Param("shopName")String shopName);
}

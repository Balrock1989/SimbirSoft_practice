package simbirSoftPractice.demo.service.interfaces;

import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dto.ItemDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> findAll();

    Optional<Item> getById(Long id);

    ItemDto save(ItemDto newItem);

    Optional<Item> deleteById(Long id);

    Optional<Item> buyItem(Long id);

    List<Item> findAllBuyItems();
}

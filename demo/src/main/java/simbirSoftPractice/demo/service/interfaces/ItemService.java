package simbirSoftPractice.demo.service.interfaces;

import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dto.ItemBuyDto;
import simbirSoftPractice.demo.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<Item> findAll();

    Item getById(Long id);

    ItemDto save(ItemDto newItem);

    Item deleteById(Long id);

    Item buyItem(Long id);

    List<ItemBuyDto> findAllBuyItems();
}

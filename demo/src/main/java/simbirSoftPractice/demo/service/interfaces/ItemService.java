package simbirSoftPractice.demo.service.interfaces;

import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<Item> findAll();

    Item getById(Long id);

    void save(ItemDto newItem);

    void deleteById(Long id);
}

package simbirSoftPractice.demo.service.interfaces;

import simbirSoftPractice.demo.dao.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();

    Item getById(Long id);

    void save(Item newItem);

    void deleteById(Long id);
}

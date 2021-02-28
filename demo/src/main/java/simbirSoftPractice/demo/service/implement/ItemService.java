package simbirSoftPractice.demo.service.implement;

import simbirSoftPractice.demo.dao.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();

    Item getById(Long id);

    void save(Item newItem);

    void deleteById(Long id);
}

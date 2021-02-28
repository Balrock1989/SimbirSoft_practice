package simbirSoftPractice.demo.service;

import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.service.implement.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public Item getById(Long id) {
        return null;
    }

    @Override
    public void save(Item newItem) {

    }

    @Override
    public void deleteById(Long id) {

    }
}

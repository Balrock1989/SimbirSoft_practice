package simbirSoftPractice.demo.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.service.interfaces.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemRepository itemRepo;

    public ItemServiceImpl(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

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

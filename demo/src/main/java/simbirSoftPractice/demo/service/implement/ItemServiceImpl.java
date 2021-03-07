package simbirSoftPractice.demo.service.implement;



import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.repository.CompanyRepository;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dto.ItemDto;
import simbirSoftPractice.demo.service.interfaces.ItemService;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;


@Service
public class ItemServiceImpl implements ItemService {

    private static Logger logger = getLogger(ItemServiceImpl.class);
    @Autowired
    private final ItemRepository itemRepo;

    @Autowired
    private final CompanyRepository companyRepo;

    public ItemServiceImpl(ItemRepository itemRepo, CompanyRepository companyRepo) {
        this.itemRepo = itemRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = (List<Item>) itemRepo.findAll();
        logger.info("successfully find all items!");
        return items;
    }

    @Override
    public ResponseEntity<Item> getById(Long id) {
        if (itemRepo.findById(id).isPresent()){
            Item item = itemRepo.findById(id).get();
            logger.info("successfully find item by id!");
            return ResponseEntity.ok(item);
        }else{
            logger.error("EXCEPTION when delete item!");
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public void save(ItemDto newItem) {
        itemRepo.save(newItem.itemDtoToItem(newItem));
        logger.info("successfully save new item!");
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        if (itemRepo.findById(id).isPresent()){
            Item deleteItem = itemRepo.findById(id).get();
            itemRepo.delete(deleteItem);
            logger.info("successfully delete item!");
            return ResponseEntity.ok("successfully delete!");
        }else {
            logger.error("EXCEPTION when delete item!");
            return ResponseEntity.ok("error!!!");
        }
    }
}

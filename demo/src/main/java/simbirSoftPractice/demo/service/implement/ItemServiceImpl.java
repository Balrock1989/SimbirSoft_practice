package simbirSoftPractice.demo.service.implement;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Status;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dao.repository.StatusRepository;
import simbirSoftPractice.demo.dto.ItemDto;
import simbirSoftPractice.demo.service.interfaces.ItemService;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
public class ItemServiceImpl implements ItemService {

    private static Logger logger = getLogger(ItemServiceImpl.class);

    private final ItemRepository itemRepo;

    private final StatusRepository statusRepo;

    public ItemServiceImpl(ItemRepository itemRepo,
                           StatusRepository statusRepo) {
        this.itemRepo = itemRepo;
        this.statusRepo = statusRepo;
    }



    @Override
    public List<Item> findAll() {
        List<Item> items = (List<Item>) itemRepo.findAll();
        logger.info("successfully find all items!");
        return items;
    }

    @Override
    public Optional<Item> getById(Long id) {
        if (itemRepo.findById(id).isPresent()){
            Optional<Item> item = itemRepo.findById(id);
            logger.info("successfully find item by id!");
            return item;
        }else{
            logger.error("EXCEPTION when delete item!");
            return Optional.empty();
        }

    }

    @Override
    public ItemDto save(ItemDto newItem) {
        itemRepo.save(newItem.itemDtoToItem(newItem));
        logger.info("successfully save new item!");
        return newItem;
    }

    @Override
    public Optional<Item> deleteById(Long id) {
        if (itemRepo.findById(id).isPresent()){
            Optional<Item> deleteItem = itemRepo.findById(id);
            itemRepo.delete(deleteItem.get());
            logger.info("successfully delete item!");
            return deleteItem;
        }else {
            logger.error("EXCEPTION when delete item!");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Item> buyItem(Long id) {
        if (itemRepo.findById(id).isPresent()){
            Optional<Item> item = itemRepo.findById(id);
            Status status = statusRepo.findById(id).get();
            status.setName("BUY");
            item.get().setStatus(status);
            itemRepo.save(item.get());
            logger.info("Item buy");
            return item;
        }else {
            logger.error("item don't buy");
            return Optional.empty();
        }
    }

    @Override
    public List<Item> findAllBuyItems() {
        List<Item> items = itemRepo.findAllBuyItems("BUY");
        logger.info("List items buy found");
        if(items.size()==0){
            logger.warn("Items list is null");
        }else{
            logger.info("Items list");
        }
        return items;
    }
}

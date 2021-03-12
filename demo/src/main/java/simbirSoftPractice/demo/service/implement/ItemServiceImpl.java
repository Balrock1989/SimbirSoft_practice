package simbirSoftPractice.demo.service.implement;



import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Status;
import simbirSoftPractice.demo.dao.repository.CompanyRepository;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dao.repository.StatusRepository;
import simbirSoftPractice.demo.dto.ItemBuyDto;
import simbirSoftPractice.demo.dto.ItemDto;
import simbirSoftPractice.demo.service.interfaces.ItemService;

import javax.persistence.EntityManager;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;


@Service
public class ItemServiceImpl implements ItemService {

    private static Logger logger = getLogger(ItemServiceImpl.class);

    private final ItemRepository itemRepo;

    @Autowired
    private EntityManager entityManager;

    private final CompanyRepository companyRepo;

    private final StatusRepository statusRepo;

    public ItemServiceImpl(ItemRepository itemRepo,
                           CompanyRepository companyRepo,
                           StatusRepository statusRepo) {
        this.itemRepo = itemRepo;
        this.companyRepo = companyRepo;
        this.statusRepo = statusRepo;
    }



    @Override
    public List<Item> findAll() {
        List<Item> items = (List<Item>) itemRepo.findAll();
        logger.info("successfully find all items!");
        return items;
    }

    @Override
    public Item getById(Long id) {
        if (itemRepo.findById(id).isPresent()){
            Item item = itemRepo.findById(id).get();
            logger.info("successfully find item by id!");
            return item;
        }else{
            logger.error("EXCEPTION when delete item!");
            return null;
        }

    }

    @Override
    public ItemDto save(ItemDto newItem) {
        itemRepo.save(newItem.itemDtoToItem(newItem));
        logger.info("successfully save new item!");
        return newItem;
    }

    @Override
    public Item deleteById(Long id) {
        if (itemRepo.findById(id).isPresent()){
            Item deleteItem = itemRepo.findById(id).get();
            itemRepo.delete(deleteItem);
            logger.info("successfully delete item!");
            return deleteItem;
        }else {
            logger.error("EXCEPTION when delete item!");
            return null;
        }
    }

    @Override
    public Item buyItem(Long id) {
        if (itemRepo.findById(id).isPresent()){
            Item item = itemRepo.findById(id).get();
            logger.info("Item found");
            Status status = statusRepo.findById(id).get();
            status.setName("BUY");
            item.setStatus(status);
            itemRepo.save(item);
            logger.info("Item buy");
            return item;
        }else {
            logger.error("item don't buy");
            return null;
        }
    }

    @Override
    public List<ItemBuyDto> findAllBuyItems() {
        List<ItemBuyDto> itemsBuyDto = entityManager.createQuery("select i.id," +
                " i.name, i.price,i.quantity,s.name from Item as i inner join"
                        +" Status as s on s.id = i.id where s.name in ?1 ")
                .setParameter(1,"BUY").getResultList();
        logger.info("List items buy found");
        if(itemsBuyDto.size()==0){
            logger.warn("Items list is null");
        }else{
            logger.info("Items list");
        }
        return itemsBuyDto;
    }
}

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
    @Autowired
    private final ItemRepository itemRepo;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private final CompanyRepository companyRepo;

    @Autowired
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

    @Override
    public ResponseEntity<String> buyItem(Long id) {
        if (itemRepo.findById(id).isPresent()){
            Item item = itemRepo.findById(id).get();
            logger.info("Item found");
            Status status = statusRepo.findById(id).get();
            status.setName("BUY");
            item.setStatus(status);
            itemRepo.save(item);
            logger.info("Item buy");
            return ResponseEntity.ok("successfully buy item");
        }else {
            logger.warn("Item don't found");
            logger.error("item don't buy");
            return ResponseEntity.ok("error buy");
        }
    }

    @Override
    public ResponseEntity<List<ItemBuyDto>> findAllBuyItems() {
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
        return ResponseEntity.ok(itemsBuyDto);
    }
}

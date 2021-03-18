package simbirSoftPractice.demo.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Package;
import simbirSoftPractice.demo.dao.entity.ShopWithItems;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dto.ItemDto;
import simbirSoftPractice.demo.dto.ShopDto;
import simbirSoftPractice.demo.service.interfaces.PackageService;
import simbirSoftPractice.demo.service.interfaces.ShopWithItemsService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
@RequiredArgsConstructor
public class ShopWithItemsServiceImpl implements ShopWithItemsService {

    private static Logger logger = getLogger(ShopWithItemsServiceImpl.class);

    private final ItemRepository itemRepo;

    private final PackageService packageService;
    @Autowired
    private EntityManager manager;

    @Override
    public List<Item> findByShop(ShopDto shopDto) {
        List<Item> itemList = manager.createQuery("select i from Item as i where " +
                "i.shopName in ?1 ")
                .setParameter(1,shopDto.getShopName())
                .getResultList();
        logger.info("find items by shop");
        return itemList;
    }

    @Override
    public Item add(ItemDto itemDto) {
        Item item = itemDto.itemDtoToItem(itemDto);
        itemRepo.save(item);
        logger.info("add items");
        return item;
    }

    @Override
    public List<Item> findAllBuyItemsInShop(ShopDto shopDto) {
        List<Item> itemList = manager.createQuery("select i from Item as i " +
                "where i.status.name in ?1 and i.shopName in ?2")
                .setParameter(1,shopDto.getStatus())
                .setParameter(2,shopDto.getShopName())
                .getResultList();
        logger.info("list by buy items in shop");
        return itemList;
    }

    @Override
    public List<Item> findAllDebitedItemsInShop(ShopDto shopDto) {
        List<Item> itemList = manager.createQuery("select i from Item as i " +
                "where i.status.name in ?1 and i.shopName in ?2 and i.dateTime between ?3 and ?4")
                .setParameter(1,shopDto.getStatus())
                .setParameter(2,shopDto.getShopName())
                .setParameter(3,shopDto.getDateTimeStart())
                .setParameter(4,shopDto.getDateTimeEnd())
                .getResultList();
        logger.info("list by debited items in shop");
        return itemList;
    }

    @Override
    public ShopDto findEarningsShop(ShopDto shopDto) {
        List<Item> itemList = findAllBuyItemsInShop(shopDto);
        int earnings = 0;
        for(int index = 0; index<itemList.size(); index++){
            earnings += itemList.get(index).getPrice();
        }
        shopDto.setEarnings(earnings);
        logger.info("find earnings shop");
        return shopDto;
    }

    @Override
    public ShopDto findMiddleBuyInShop(ShopDto shopDto) {
        List<ShopWithItems> reports = manager.createQuery("select s from ShopWithItems as s " +
                "where s.shopName in ?1 ")
                .setParameter(1,shopDto.getShopName())
                .getResultList();
        int middleEarnings = 0;
        if(!reports.isEmpty()) {
            for (int index = 0; index < reports.size(); index++) {
                middleEarnings += reports.get(index).getPay();
            }
            shopDto.setMiddlePrice(middleEarnings);
            logger.info("Middle earnings done!");
        }
        return shopDto;
    }
}

package simbirSoftPractice.demo.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.ShopWithItems;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dao.repository.ShopWithItemsRepository;
import simbirSoftPractice.demo.dto.ItemDto;
import simbirSoftPractice.demo.dto.ShopDto;
import simbirSoftPractice.demo.service.interfaces.ShopWithItemsService;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
@RequiredArgsConstructor
public class ShopWithItemsServiceImpl implements ShopWithItemsService {

    private static Logger logger = getLogger(ShopWithItemsServiceImpl.class);

    private final ItemRepository itemRepo;
    private final ShopWithItemsRepository shopWithItemsRepo;

    @Override
    public List<Item> findByShop(ShopDto shopDto) {
        List<Item> itemList = itemRepo.findByShopName(shopDto.getShopName());
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
        List<Item> itemList =
                itemRepo.findAllBuyItemsInShop(shopDto.getStatus(),shopDto.getShopName());
        logger.info("list by buy items in shop");
        return itemList;
    }

    @Override
    public List<Item> findAllDebitedItemsInShop(ShopDto shopDto) {
        logger.info(shopDto.toString());
        List<Item> itemList = itemRepo.findAllDebitedItemsInShop(shopDto.getStatus(),
                                                                shopDto.getShopName(),
                                                                shopDto.getDateTimeStart(),
                                                                shopDto.getDateTimeEnd());
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
        List<ShopWithItems> reports = shopWithItemsRepo.findMiddleBuyInShop(shopDto.getShopName());
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

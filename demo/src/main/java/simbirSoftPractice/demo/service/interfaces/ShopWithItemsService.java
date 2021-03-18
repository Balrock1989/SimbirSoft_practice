package simbirSoftPractice.demo.service.interfaces;

import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.ShopWithItems;
import simbirSoftPractice.demo.dto.ItemDto;
import simbirSoftPractice.demo.dto.ShopDto;

import java.util.List;

public interface ShopWithItemsService {

    List<Item> findByShop(ShopDto shopDto);

    Item add(ItemDto itemDto);

    List<Item> findAllBuyItemsInShop(ShopDto shopDto);

    List<Item> findAllDebitedItemsInShop(ShopDto shopDto);

    ShopDto findEarningsShop(ShopDto shopDto);

    ShopDto findMiddleBuyInShop(ShopDto shopDto);
}

package simbirSoftPractice.demo.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.ShopWithItems;
import simbirSoftPractice.demo.dao.entity.Status;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dao.repository.ShopWithItemsRepository;
import simbirSoftPractice.demo.dto.ShopDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class ShopWithItemsServiceImplTest {

    @Autowired
    private ShopWithItemsServiceImpl service;

    @MockBean
    private ItemRepository itemRepo;

    @MockBean
    private ShopWithItemsRepository shopWithItemsRepo;

    @Test
    void findByShop() {
        Item item = new Item();
        item.setId(1L);
        item.setShopName("samsung");
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        ShopDto shopDto = new ShopDto();
        shopDto.setShopName("samsung");
        Mockito.doReturn(itemList)
                .when(itemRepo)
                .findByShopName("samsung");
        boolean isItemByShopFind = service.findByShop(shopDto).isEmpty();
        Assert.assertFalse(isItemByShopFind);
        Mockito.verify(itemRepo,Mockito.times(1)).findByShopName("samsung");
    }

    @Test
    void findByShopFail(){
        ShopDto shopDto = new ShopDto();
        boolean isItemByShopFind = service.findByShop(shopDto).isEmpty();
        Assert.assertTrue(isItemByShopFind);
        Mockito.verify(itemRepo,Mockito.times(1)).findByShopName(null);
    }

    @Test
    void findAllBuyItemsInShop() {
        Item item = new Item();
        item.setId(1L);
        item.setShopName("samsung");
        Status status = new Status();
        status.setName("BUY");
        item.setStatus(status);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        ShopDto shopDto = new ShopDto();
        shopDto.setShopName("samsung");
        shopDto.setStatus("BUY");
        Mockito.doReturn(itemList)
                .when(itemRepo)
                .findAllBuyItemsInShop("BUY","samsung");
        boolean isItemByShopFind = service.findAllBuyItemsInShop(shopDto).isEmpty();
        Assert.assertFalse(isItemByShopFind);
        Mockito.verify(itemRepo,Mockito.times(1))
                .findAllBuyItemsInShop("BUY","samsung");
    }

    @Test
    void findAllBuyItemsInShopFail(){
        ShopDto shopDto = new ShopDto();
        shopDto.setShopName("samsung");
        shopDto.setStatus("BUY");
        boolean isItemByShopFind = service.findAllBuyItemsInShop(shopDto).isEmpty();
        Assert.assertTrue(isItemByShopFind);
        Mockito.verify(itemRepo,Mockito.times(1))
                .findAllBuyItemsInShop("BUY","samsung");
    }

    @Test
    void findAllDebitedItemsInShop() {
        Item item = new Item();
        item.setId(1L);
        item.setShopName("samsung");
        Status status = new Status();
        status.setName("DEBITED");
        item.setStatus(status);
        item.setDateTime(LocalDateTime.of(2000,1,1,1,1));
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        ShopDto shopDto = new ShopDto();
        shopDto.setShopName("samsung");
        shopDto.setStatus("DEBITED");
        shopDto.setDateTimeStart(LocalDateTime.of(1990,1,1,1,1));
        shopDto.setDateTimeEnd(LocalDateTime.of(2010,1,1,1,1));
        Mockito.doReturn(itemList)
                .when(itemRepo)
                .findAllDebitedItemsInShop("DEBITED",
                                            "samsung",
                        LocalDateTime.of(1990,1,1,1,1),
                        LocalDateTime.of(2010,1,1,1,1));
        boolean isItemDebitedByShopFind = service.findAllDebitedItemsInShop(shopDto).isEmpty();
        Assert.assertFalse(isItemDebitedByShopFind);
        Mockito.verify(itemRepo,Mockito.times(1))
                .findAllDebitedItemsInShop("DEBITED","samsung",
                        LocalDateTime.of(1990,1,1,1,1),
                        LocalDateTime.of(2010,1,1,1,1));
    }

    @Test
    void findAllDebitedItemsInShopFail(){
        ShopDto shopDto = new ShopDto();
        shopDto.setShopName("samsung");
        shopDto.setStatus("DEBITED");
        shopDto.setDateTimeStart(LocalDateTime.of(1990,1,1,1,1));
        shopDto.setDateTimeEnd(LocalDateTime.of(2010,1,1,1,1));
        boolean isItemDebitedByShopFind = service.findAllDebitedItemsInShop(shopDto).isEmpty();
        Assert.assertTrue(isItemDebitedByShopFind);
        Mockito.verify(itemRepo,Mockito.times(1))
                .findAllDebitedItemsInShop("DEBITED","samsung",
                        LocalDateTime.of(1990,1,1,1,1),
                        LocalDateTime.of(2010,1,1,1,1));
    }

    @Test
    void findMiddleBuyInShop() {
        ShopWithItems itemOne = new ShopWithItems();
        itemOne.setId(1L);
        itemOne.setShopName("samsung");
        itemOne.setPay(150);
        ShopWithItems itemTwo = new ShopWithItems();
        itemTwo.setId(2L);
        itemTwo.setShopName("samsung");
        itemTwo.setPay(250);
        List<ShopWithItems> shopWithItemsList = new ArrayList<>();
        shopWithItemsList.add(itemOne);
        shopWithItemsList.add(itemTwo);
        Mockito.doReturn(shopWithItemsList)
                .when(shopWithItemsRepo)
                .findMiddleBuyInShop("samsung");
        ShopDto shopDto = new ShopDto();
        shopDto.setShopName("samsung");
        shopDto = service.findMiddleBuyInShop(shopDto);
        Assert.assertEquals(400,shopDto.getMiddlePrice());
        Assert.assertEquals("samsung",shopDto.getShopName());
        Mockito.verify(shopWithItemsRepo,Mockito.times(1))
                .findMiddleBuyInShop("samsung");
    }
}
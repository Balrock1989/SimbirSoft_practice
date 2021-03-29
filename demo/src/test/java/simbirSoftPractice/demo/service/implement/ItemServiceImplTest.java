package simbirSoftPractice.demo.service.implement;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Status;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dao.repository.StatusRepository;
import simbirSoftPractice.demo.dto.ItemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ItemServiceImplTest {

    @Autowired
    private ItemServiceImpl itemService;

    @MockBean
    private ItemRepository itemRepo;

    @MockBean
    private StatusRepository statusRepo;

    @Test
    void getByIdFail() {
        boolean isItemSave = itemService.getById(1L).isPresent();
        Assert.assertFalse(isItemSave);
        Mockito.verify(itemRepo,Mockito.times(1)).findById(1L);
    }

    @Test
    void getById(){
        Item item = new Item();
        item.setId(1L);
        item.setName("myName");
        Optional<Item> itemOptional = Optional.of(item);
        Mockito.doReturn(itemOptional)
                .when(itemRepo)
                .findById(1L);
        boolean isItemFind = itemService.getById(1L).isPresent();
        Assert.assertTrue(isItemFind);
        Mockito.verify(itemRepo,Mockito.times(2)).findById(1L);
    }

    @Test
    void save() {
        ItemDto newItemDto = new ItemDto();
        ItemDto saveItem = itemService.save(newItemDto);
        boolean isItemSave = saveItem != null;
        Assert.assertTrue(isItemSave);
        Item newItem = newItemDto.itemDtoToItem(newItemDto);
        Mockito.verify(itemRepo,Mockito.times(1)).save(newItem);
    }

    @Test
    void deleteById() {
        Item item = new Item();
        item.setId(1L);
        item.setName("myName");
        Optional<Item> itemOptional = Optional.of(item);
        Mockito.doReturn(itemOptional)
                .when(itemRepo)
                .findById(1L);
        boolean isItemDelete = itemService.deleteById(1L).isPresent();
        Assert.assertTrue(isItemDelete);
        Mockito.verify(itemRepo,Mockito.times(2)).findById(1L);
        Mockito.verify(itemRepo,Mockito.times(1)).delete(item);
    }

    @Test
    void deleteByIdFail(){
        boolean isItemDelete = itemService.deleteById(1L).isPresent();
        Assert.assertFalse(isItemDelete);
        Mockito.verify(itemRepo,Mockito.times(1)).findById(1L);
    }

    @Test
    void buyItem() {
        Item item = new Item();
        item.setId(1L);
        item.setName("myName");
        Status status = new Status();
        status.setName("BUY");
        item.setStatus(status);
        Optional<Item> itemOptional = Optional.of(item);
        Mockito.doReturn(itemOptional)
                .when(itemRepo)
                .findById(1L);
        Optional<Status> statusOptional = Optional.of(status);
        Mockito.doReturn(statusOptional)
                .when(statusRepo)
                .findById(1L);
        boolean isItemBuy = itemService.buyItem(1L).isPresent();
        Assert.assertTrue(isItemBuy);
        Mockito.verify(itemRepo,Mockito.times(2)).findById(1L);
        Mockito.verify(itemRepo,Mockito.times(1)).save(item);
        Mockito.verify(statusRepo,Mockito.times(1)).findById(1L);
    }

    @Test
    void buyItemFail(){
        boolean isItemBuy = itemService.buyItem(1L).isPresent();
        Assert.assertFalse(isItemBuy);
        Mockito.verify(itemRepo,Mockito.times(1)).findById(1L);
    }

    @Test
    void findAllBuyItems() {
        Item itemOne = new Item();
        Item itemTwo = new Item();
        List<Item> itemList = new ArrayList<>();
        itemList.add(itemOne);
        itemList.add(itemTwo);
        Mockito.doReturn(itemList)
                .when(itemRepo)
                .findAllBuyItems("BUY");
        boolean isListItemsBuy = itemService.findAllBuyItems().isEmpty();
        Assert.assertFalse(isListItemsBuy);
        Mockito.verify(itemRepo,Mockito.times(1)).findAllBuyItems("BUY");
    }

    @Test
    void findAllBuyItemsFail(){
        boolean isListItemsBuy = itemService.findAllBuyItems().isEmpty();
        Assert.assertTrue(isListItemsBuy);
        Mockito.verify(itemRepo,Mockito.times(1)).findAllBuyItems("BUY");

    }

    @Test
    void findAllByInaccurateMatchNameItem() {
        Item item = new Item();
        item.setId(1L);
        item.setName("new-product");
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        Mockito.doReturn(itemList)
                .when(itemRepo)
                .findAllByInaccurateMatchNameItem("w-p");
        boolean isItemFind = itemService.findAllByInaccurateMatchNameItem("w-p").isEmpty();
        Assert.assertFalse(isItemFind);
        Mockito.verify(itemRepo,Mockito.times(1)).findAllByInaccurateMatchNameItem("w-p");
    }

    @Test
    void findAllByInaccurateMatchNameItemFail(){
        boolean isItemFind = itemService.findAllByInaccurateMatchNameItem("w-p").isEmpty();
        Assert.assertTrue(isItemFind);
        Mockito.verify(itemRepo,Mockito.times(1)).findAllByInaccurateMatchNameItem("w-p");
    }

    @Test
    void findAllByInaccurateMatchProductGroup() {
        Item itemOne = new Item();
        itemOne.setId(1L);
        itemOne.setProductGroup("it");
        Item itemTwo = new Item();
        itemTwo.setId(2L);
        itemTwo.setProductGroup("it-group");
        List<Item> itemList = new ArrayList<>();
        itemList.add(itemOne);
        itemList.add(itemTwo);
        Mockito.doReturn(itemList)
                .when(itemRepo)
                .findAllByInaccurateMatchProductGroup("t");
        boolean isListItemFind = itemService.findAllByInaccurateMatchProductGroup("t").isEmpty();
        Assert.assertFalse(isListItemFind);
        Mockito.verify(itemRepo,Mockito.times(1)).findAllByInaccurateMatchProductGroup("t");
    }

    @Test
    void findAllByInaccurateMatchProductGroupFail(){
        boolean isListItemFind = itemService.findAllByInaccurateMatchProductGroup("t").isEmpty();
        Assert.assertTrue(isListItemFind);
        Mockito.verify(itemRepo,Mockito.times(1)).findAllByInaccurateMatchProductGroup("t");
    }
}
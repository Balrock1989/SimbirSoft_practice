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
import simbirSoftPractice.demo.dao.entity.PackageDebited;
import simbirSoftPractice.demo.dao.entity.Status;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dao.repository.PackageDebitedRepository;
import simbirSoftPractice.demo.dto.Mapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PackageDebitedServiceImplTest {

    @Autowired
    private PackageDebitedServiceImpl packageDebitedService;

    @MockBean
    private PackageDebitedRepository packageDebitedRepo;

    @MockBean
    private ItemRepository itemRepo;

    private Mapper mapper = new Mapper();

    @Test
    void addItem() {
        Item item = new Item();
        item.setId(1L);
        item.setName("myName");
        Status status = new Status();
        status.setName("ONSTORAGE");
        item.setStatus(status);
        Optional<Item> itemOptional = Optional.of(item);
        Mockito.doReturn(itemOptional)
                .when(itemRepo)
                .findById(1L);
        PackageDebited packageDebited = mapper.itemToPackageDebited(itemOptional.get());
        boolean isItemAdd = packageDebitedService.addItem(1L).isPresent();
        Assert.assertTrue(isItemAdd);
        Mockito.verify(itemRepo,Mockito.times(2)).findById(1L);
    }

    @Test
    void deleteItem() {
        PackageDebited itemDebited = new PackageDebited();
        itemDebited.setId(1L);
        Optional<PackageDebited> debitedOptional = Optional.of(itemDebited);
        Mockito.doReturn(debitedOptional)
                .when(packageDebitedRepo)
                .findById(1L);
        boolean isItemDebitedDelete = packageDebitedService.deleteItem(1L).isPresent();
        Assert.assertTrue(isItemDebitedDelete);
        Mockito.verify(packageDebitedRepo,Mockito.times(2)).findById(1L);
        Mockito.verify(packageDebitedRepo,Mockito.times(1)).delete(itemDebited);
    }

    @Test
    void deleteItemFail(){
        boolean isItemDebitedDelete = packageDebitedService.deleteItem(1L).isPresent();
        Assert.assertFalse(isItemDebitedDelete);
        Mockito.verify(packageDebitedRepo,Mockito.times(1)).findById(1L);
    }
}
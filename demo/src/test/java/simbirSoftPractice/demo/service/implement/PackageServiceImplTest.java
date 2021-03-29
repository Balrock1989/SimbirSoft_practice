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
import simbirSoftPractice.demo.dao.entity.Package;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dao.repository.PackageRepository;
import simbirSoftPractice.demo.dto.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PackageServiceImplTest {

    @Autowired
    private PackageServiceImpl packageService;

    @MockBean
    private PackageRepository packageRepo;

    @MockBean
    private ItemRepository itemRepo;

    @Test
    void addItem() {
        Item item = new Item();
        item.setId(1L);
        Optional<Item> itemOptional = Optional.of(item);
        Mockito.doReturn(itemOptional)
                .when(itemRepo)
                .findById(1L);
        boolean isItemPackageAdd = packageService.addItem(1L).isPresent();
        Assert.assertTrue(isItemPackageAdd);
        Mockito.verify(itemRepo,Mockito.times(2)).findById(1L);
    }

    @Test
    void addItemFail(){
        boolean isItemPackageAdd = packageService.addItem(1L).isPresent();
        Assert.assertFalse(isItemPackageAdd);
        Mockito.verify(itemRepo,Mockito.times(1)).findById(1L);

    }

    @Test
    void deleteItem() {
        Package aPackage = new Package();
        aPackage.setId(1L);
        Optional<Package> packageOptional = Optional.of(aPackage);
        Mockito.doReturn(packageOptional)
                .when(packageRepo)
                .findById(1L);
        boolean isItemPackageDelete = packageService.deleteItem(1L).isPresent();
        Assert.assertTrue(isItemPackageDelete);
        Mockito.verify(packageRepo,Mockito.times(2)).findById(1L);
        Mockito.verify(packageRepo,Mockito.times(1)).delete(packageOptional.get());
    }

    @Test
    void deleteItemFail(){
        boolean isItemPackageDelete = packageService.deleteItem(1L).isPresent();
        Assert.assertFalse(isItemPackageDelete);
        Mockito.verify(packageRepo,Mockito.times(1)).findById(1L);
    }

    @Test
    void deleteAllList() {
        Package packageOne = new Package();
        packageOne.setId(1L);
        Package packageTwo = new Package();
        packageTwo.setId(2L);
        List<Package> packageList = new ArrayList<>();
        packageList.add(packageOne);
        packageList.add(packageTwo);
        Mockito.doReturn(packageList)
                .when(packageRepo)
                .findAll();
        boolean isListPackageDelete = packageService.deleteAllList().isEmpty();
        Assert.assertFalse(isListPackageDelete);
        Mockito.verify(packageRepo,Mockito.times(2)).findAll();
        Mockito.verify(packageRepo,Mockito.times(1)).deleteAll();
    }
}
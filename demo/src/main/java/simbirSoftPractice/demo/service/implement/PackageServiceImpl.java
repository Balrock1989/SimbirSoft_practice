package simbirSoftPractice.demo.service.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Package;
import simbirSoftPractice.demo.dao.entity.ShopWithItems;
import simbirSoftPractice.demo.dao.entity.Status;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dao.repository.PackageRepository;
import simbirSoftPractice.demo.dao.repository.ShopWithItemsRepository;
import simbirSoftPractice.demo.dto.Mapper;
import simbirSoftPractice.demo.service.interfaces.PackageService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepo;

    private final ItemRepository itemRepo;

    private final ShopWithItemsRepository shopWithItemsRepo;

    private Mapper mapper = new Mapper();

    @Override
    public Optional<Item> addItem(Long id) {
        if(itemRepo.findById(id).isPresent()){
            Optional<Item> item = itemRepo.findById(id);
            Package aPackage = mapper.itemToPackage(item.get());
            packageRepo.save(aPackage);
            return item;
        }else{
            log.warn("Error add in package! Method: addItem "+id+", class: PackageServiceImpl");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Package> deleteItem(Long id) {
        if (packageRepo.findById(id).isPresent()){
            Optional<Package> aPackage = packageRepo.findById(id);
            packageRepo.delete(aPackage.get());
            return aPackage;
        }else{
            log.warn("Item from package don't delete! Method: deleteItem "+id+", class: PackageServiceImpl");
            return Optional.empty();
        }
    }

    @Override
    public List<Package> deleteAllList() {
        if (packageRepo.findAll().size() != 0){
            List<Package> packageList = packageRepo.findAll();
            packageRepo.deleteAll();
            return packageList;
        }else{
            log.warn("List from package is empty! Method: deleteAllList , class: PackageServiceImpl");
            return null;
        }
    }

    @Override
    public List<Package> findAll() {
        List<Package> packageList = packageRepo.findAll();
        return packageList;
    }

    @Override
    public List<Package> buyItems() {
        Status status = new Status();
        status.setName("BUY");
        if (packageRepo.findAll().size() != 0){
            List<Package> packageList = packageRepo.findAll();
            List<Item> itemList = (List<Item>) itemRepo.findAll();
            for(int i = 0; i<packageList.size(); i++){
                for(int j = 0; j<itemList.size(); j++){
                    Package aPackage = packageList.get(i);
                    Long aPackageId = aPackage.getItemId();
                    Item item = itemList.get(j);
                    Long itemId = item.getId();
                    if(aPackageId.equals(itemId)){
                        itemList.get(j).setStatus(status);
                    }
                }
            }
            List<Package> packageListAfterChangeStatus = packageRepo.findAll();
            createReport(packageListAfterChangeStatus);
            packageRepo.deleteAll();
            return packageListAfterChangeStatus;
        }else{
            log.warn("Error when it bought items! , class: PackageServiceImpl");
            return null;
        }
    }

    private ShopWithItems createReport(List<Package> packageList){
        ShopWithItems report = new ShopWithItems();
        report.setQuantity(packageList.size());
        int pay = 0 ;
        for(int index = 0; index<packageList.size();index++){
            pay += packageList.get(index).getPrice();
        }
        report.setPay(pay);
        report.setShopName(packageList.get(0).getShopName());
        shopWithItemsRepo.save(report);
        return report;
    }
}

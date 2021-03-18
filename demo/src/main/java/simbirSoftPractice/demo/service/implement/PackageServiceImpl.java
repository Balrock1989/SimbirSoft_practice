package simbirSoftPractice.demo.service.implement;

import lombok.RequiredArgsConstructor;
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

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    private static Logger logger = getLogger(PackageServiceImpl.class);

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
            logger.info("successfully add in package");
            return item;
        }else{
            logger.warn("error add in package");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Package> deleteItem(Long id) {
        if (packageRepo.findById(id).isPresent()){
            Optional<Package> aPackage = packageRepo.findById(id);
            packageRepo.delete(aPackage.get());
            logger.info("item from package delete");
            return aPackage;
        }else{
            logger.warn("item from package don't delete");
            return Optional.empty();
        }
    }

    @Override
    public List<Package> deleteAllList() {
        if (packageRepo.findAll().size() != 0){
            List<Package> packageList = packageRepo.findAll();
            packageRepo.deleteAll();
            logger.info("List from package delete");
            return packageList;
        }else{
            logger.warn("list from package is empty");
            return null;
        }
    }

    @Override
    public List<Package> findAll() {
        List<Package> packageList = packageRepo.findAll();
        logger.info("List from package found");
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
            logger.info("successfully buy");
            return packageListAfterChangeStatus;
        }else{
            logger.warn("error buy");
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
        logger.info(" create report");
        return report;
    }
}

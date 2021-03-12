package simbirSoftPractice.demo.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Package;
import simbirSoftPractice.demo.dao.entity.Status;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dao.repository.PackageRepository;
import simbirSoftPractice.demo.service.interfaces.PackageService;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    private static Logger logger = getLogger(PackageServiceImpl.class);

    private final PackageRepository packageRepo;

    private final ItemRepository itemRepo;

    private Package itemToPackage(Item item){
        Package aPackage = new Package();
        aPackage.setItemId(item.getId());
        aPackage.setName(item.getName());
        aPackage.setPrice(item.getPrice());
        aPackage.setQuantity(item.getQuantity());
        return aPackage;
    }

    @Override
    public Package addItem(Long id) {
        if(itemRepo.findById(id).isPresent()){
            Item item = itemRepo.findById(id).get();
            Package aPackage = itemToPackage(item);
            packageRepo.save(aPackage);
            logger.info("successfully add in package");
            return aPackage;
        }else{
            logger.warn("error add in package");
            return null;
        }
    }

    @Override
    public Package deleteItem(Long id) {
        if (packageRepo.findById(id).isPresent()){
            logger.info("item from package found");
            Package aPackage = packageRepo.findById(id).get();
            packageRepo.delete(aPackage);
            logger.info("item from package delete");
            return aPackage;
        }else{
            logger.warn("item from package don't delete");
            return null;
        }
    }

    @Override
    public List<Package> deleteAllList() {
        if (packageRepo.findAll().size() != 0){
            logger.info("list from package found");
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
            for(int index = 0; index<packageList.size(); index++){
                for(int jindex = 0; jindex<itemList.size(); jindex++){
                    Package aPackage = packageList.get(index);
                    Long aPackageId = aPackage.getItemId();
                    Item item = itemList.get(jindex);
                    Long itemId = item.getId();
                    if(aPackageId.equals(itemId)){
                        itemList.get(jindex).setStatus(status);
                        logger.info("Item buy:"+itemList.get(jindex).toString());
                    }
                }
            }
            List<Package> aPackageList = packageRepo.findAll();
            packageRepo.deleteAll();
            logger.info("successfully buy");
            return aPackageList;
        }else{
            logger.warn("error buy");
            return null;
        }
    }
}

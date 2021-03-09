package simbirSoftPractice.demo.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final PackageRepository packageRepo;

    @Autowired
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
    public ResponseEntity<String> addItem(Long id) {
        if(itemRepo.findById(id).isPresent()){
            Item item = itemRepo.findById(id).get();
            packageRepo.save(itemToPackage(item));
            logger.info("successfully add in package");
            return ResponseEntity.ok("successfully add in package");
        }else{
            logger.warn("error add in package");
            return ResponseEntity.ok("error add in package");
        }
    }

    @Override
    public ResponseEntity<String> deleteItem(Long id) {
        if (packageRepo.findById(id).isPresent()){
            logger.info("item from package found");
            packageRepo.deleteById(id);
            logger.info("item from package delete");
            return ResponseEntity.ok("successfully delete");
        }else{
            logger.warn("item from package don't found");
            logger.warn("item from package don't delete");
            return ResponseEntity.ok("error delete item");
        }
    }

    @Override
    public ResponseEntity<String> deleteAllList() {
        if (packageRepo.findAll().size() != 0){
            logger.info("list from package found");
            packageRepo.deleteAll();
            logger.info("List from package delete");
            return ResponseEntity.ok("successfully clear package");
        }else{
            logger.warn("list from package is empty");
            return ResponseEntity.ok("package is empty");
        }
    }

    @Override
    public ResponseEntity<List<Package>> findAll() {
        List<Package> packageList = packageRepo.findAll();
        logger.info("List from package found");
        return ResponseEntity.ok(packageList);
    }

    @Override
    public ResponseEntity<String> buyItems() {
        Status status = new Status();
        status.setName("BUY");
        if (packageRepo.findAll().size() != 0){
            List<Package> packageList = packageRepo.findAll();
            List<Item> itemList = (List<Item>) itemRepo.findAll();
            for(int index = 0; index<packageList.size(); index++){
                for(int j = 0; j<itemList.size(); j++){
                    if(packageList.get(index).getItemId().equals(itemList.get(j).getId())){
                        itemList.get(j).setStatus(status);
                        logger.info("Item buy:"+itemList.get(j).toString());
                    }
                }
            }
            packageRepo.deleteAll();
            logger.info("successfully buy");
            return ResponseEntity.ok("successfully buy");
        }else{
            logger.warn("error buy");
            return ResponseEntity.ok("error buy");
        }
    }
}

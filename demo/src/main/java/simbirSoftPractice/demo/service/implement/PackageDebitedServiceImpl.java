package simbirSoftPractice.demo.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Package;
import simbirSoftPractice.demo.dao.entity.PackageDebited;
import simbirSoftPractice.demo.dao.entity.Status;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dao.repository.PackageDebitedRepository;
import simbirSoftPractice.demo.service.interfaces.PackageDebitedService;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
@RequiredArgsConstructor
public class PackageDebitedServiceImpl implements PackageDebitedService {

    private static Logger logger = getLogger(PackageDebitedServiceImpl.class);
    @Autowired
    private final PackageDebitedRepository debitedRepo;

    @Autowired
    private final ItemRepository itemRepo;

    private PackageDebited itemToPackageDebited(Item item){
        PackageDebited packageDebited = new PackageDebited();
        packageDebited.setItemId(item.getId());
        packageDebited.setName(item.getName());
        packageDebited.setPrice(item.getPrice());
        packageDebited.setQuantity(item.getQuantity());
        packageDebited.setStatus(item.getStatus().getName());
        return packageDebited;
    }

    @Override
    public ResponseEntity<String> addItem(Long id) {
        if(itemRepo.findById(id).isPresent()){
            Item item = itemRepo.findById(id).get();
            debitedRepo.save(itemToPackageDebited(item));
            logger.info("successfully add in package");
            return ResponseEntity.ok("successfully add in package");
        }else{
            logger.warn("error add in package");
            return ResponseEntity.ok("error add in package");
        }
    }

    @Override
    public ResponseEntity<String> deleteItem(Long id) {
        if (debitedRepo.findById(id).isPresent()){
            logger.info("item from package found");
            debitedRepo.deleteById(id);
            logger.info("item from package delete");
            return ResponseEntity.ok("successfully delete");
        }else{
            logger.warn("item from package don't found");
            logger.warn("item from package don't delete");
            return ResponseEntity.ok("error delete item");
        }
    }

    @Override
    public ResponseEntity<String> listToDebited() {
        Status status = new Status();
        status.setName("DEBITED");
        if (debitedRepo.findAll().size() != 0){
            List<PackageDebited> packageDebitedList = debitedRepo.findAll();
            List<Item> itemList = (List<Item>) itemRepo.findAll();
            for(int index = 0; index<packageDebitedList.size(); index++){
                for(int j = 0; j<itemList.size(); j++){
                    if(packageDebitedList.get(index).getItemId().equals(itemList.get(j).getId())){
                        itemList.get(j).setStatus(status);
                        logger.info("Item debited:"+itemList.get(j).toString());
                    }
                }
            }
            debitedRepo.deleteAll();
            logger.info("successfully debited");
            return ResponseEntity.ok("successfully debited");
        }else{
            logger.warn("error debited");
            return ResponseEntity.ok("error debited");
        }
    }

    @Override
    public ResponseEntity<List<PackageDebited>> findAll() {
        List<PackageDebited> packageList = debitedRepo.findAll();
        logger.info("List from package found");
        return ResponseEntity.ok(packageList);
    }
}

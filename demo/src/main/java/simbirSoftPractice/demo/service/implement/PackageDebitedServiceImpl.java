package simbirSoftPractice.demo.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
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

    private final PackageDebitedRepository debitedRepo;

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
    public ResponseEntity<PackageDebited> addItem(Long id) {
        if(itemRepo.findById(id).isPresent()){
            Item item = itemRepo.findById(id).get();
            PackageDebited itemDebited = itemToPackageDebited(item);
            debitedRepo.save(itemDebited);
            logger.info("successfully add in package");
            return ResponseEntity.ok(itemDebited);
        }else{
            logger.warn("error add in package");
            return ResponseEntity.ok(null);
        }
    }

    @Override
    public ResponseEntity<PackageDebited> deleteItem(Long id) {
        if (debitedRepo.findById(id).isPresent()){
            logger.info("item from package found");
            PackageDebited itemDebited = debitedRepo.findById(id).get();
            debitedRepo.delete(itemDebited);
            logger.info("item from package delete");
            return ResponseEntity.ok(itemDebited);
        }else{
            logger.warn("item from package don't delete");
            return ResponseEntity.ok(null);
        }
    }

    @Override
    public ResponseEntity<List<PackageDebited>> listToDebited() {
        Status status = new Status();
        status.setName("DEBITED");
        if (debitedRepo.findAll().size() != 0){
            List<PackageDebited> packageDebitedList = debitedRepo.findAll();
            List<Item> itemList = (List<Item>) itemRepo.findAll();
            for(int index = 0; index<packageDebitedList.size(); index++){
                for(int jindex = 0; jindex<itemList.size(); jindex++){
                    PackageDebited itemDebited = packageDebitedList.get(index);
                    Long itemDebitedId = itemDebited.getItemId();
                    Item item = itemList.get(jindex);
                    Long itemId = item.getId();
                    if(itemDebitedId.equals(itemId)){
                        itemList.get(jindex).setStatus(status);
                        logger.info("Item debited:"+itemList.get(jindex).toString());
                    }
                }
            }
            List<PackageDebited> debitedList = debitedRepo.findAll();
            debitedRepo.deleteAll();
            logger.info("successfully debited");
            return ResponseEntity.ok(debitedList);
        }else{
            logger.warn("error debited");
            return ResponseEntity.ok(null);
        }
    }

    @Override
    public ResponseEntity<List<PackageDebited>> findAll() {
        List<PackageDebited> packageList = debitedRepo.findAll();
        logger.info("List from package found");
        return ResponseEntity.ok(packageList);
    }
}

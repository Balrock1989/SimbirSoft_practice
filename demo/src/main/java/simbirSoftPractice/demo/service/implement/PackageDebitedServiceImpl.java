package simbirSoftPractice.demo.service.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.PackageDebited;
import simbirSoftPractice.demo.dao.entity.Status;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dao.repository.PackageDebitedRepository;
import simbirSoftPractice.demo.dto.Mapper;
import simbirSoftPractice.demo.service.interfaces.PackageDebitedService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PackageDebitedServiceImpl implements PackageDebitedService {

    private final PackageDebitedRepository debitedRepo;

    private final ItemRepository itemRepo;

    private Mapper mapper = new Mapper();

    @Override
    public Optional<Item> addItem(Long id) {
        if(itemRepo.findById(id).isPresent()){
            Optional<Item> item = itemRepo.findById(id);
            PackageDebited itemDebited = mapper.itemToPackageDebited(item.get());
            debitedRepo.save(itemDebited);
            return item;
        }else{
            log.warn("Error add in package! Method: addItem "+id+" , class: PackageDebitedServiceImpl");
            return Optional.empty();
        }
    }

    @Override
    public Optional<PackageDebited> deleteItem(Long id) {
        if (debitedRepo.findById(id).isPresent()){
            Optional<PackageDebited> itemDebited = debitedRepo.findById(id);
            debitedRepo.delete(itemDebited.get());
            return itemDebited;
        }else{
            log.warn("Item from package don't delete! Method: deleteItem "+id+" , class: PackageDebitedServiceImpl");
            return Optional.empty();
        }
    }

    @Override
    public List<PackageDebited> listToDebited() {
        Status status = new Status();
        status.setName("DEBITED");
        if (debitedRepo.findAll().size() != 0){
            List<PackageDebited> packageDebitedList = debitedRepo.findAll();
            List<Item> itemList = (List<Item>) itemRepo.findAll();
            for(int i = 0; i<packageDebitedList.size(); i++){
                for(int j = 0; j<itemList.size(); j++){
                    PackageDebited itemDebited = packageDebitedList.get(i);
                    Long itemDebitedId = itemDebited.getItemId();
                    Item item = itemList.get(j);
                    Long itemId = item.getId();
                    if(itemDebitedId.equals(itemId)){
                        itemList.get(j).setStatus(status);
                    }
                }
            }
            List<PackageDebited> debitedList = debitedRepo.findAll();
            debitedRepo.deleteAll();
            return debitedList;
        }else{
            log.warn("Error debited! Method: listToDebited , class: PackageDebitedServiceImpl");
            return null;
        }
    }

    @Override
    public List<PackageDebited> findAll() {
        List<PackageDebited> packageList = debitedRepo.findAll();
        return packageList;
    }
}

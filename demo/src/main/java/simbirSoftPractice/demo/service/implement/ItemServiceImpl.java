package simbirSoftPractice.demo.service.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Status;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dao.repository.StatusRepository;
import simbirSoftPractice.demo.dto.ItemDto;
import simbirSoftPractice.demo.service.interfaces.ItemService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepo;

    private final StatusRepository statusRepo;

    @Override
    public List<Item> findAll() {
        List<Item> items = (List<Item>) itemRepo.findAll();
        return items;
    }

    @Override
    public Optional<Item> getById(Long id) {
        if (itemRepo.findById(id).isPresent()){
            Optional<Item> item = itemRepo.findById(id);
            return item;
        }else{
            log.error("EXCEPTION when delete item! Method: getById " + id+", class: ItemServiceImpl");
            return Optional.empty();
        }

    }

    @Override
    public ItemDto save(ItemDto newItem) {
        itemRepo.save(newItem.itemDtoToItem(newItem));
        return newItem;
    }

    @Override
    public Optional<Item> deleteById(Long id) {
        if (itemRepo.findById(id).isPresent()){
            Optional<Item> deleteItem = itemRepo.findById(id);
            itemRepo.delete(deleteItem.get());
            return deleteItem;
        }else {
            log.error("EXCEPTION when delete item! Method: deleteById "+id+", class: ItemServiceImpl");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Item> buyItem(Long id) {
        if (itemRepo.findById(id).isPresent()){
            Optional<Item> item = itemRepo.findById(id);
            Status status = statusRepo.findById(id).get();
            status.setName("BUY");
            item.get().setStatus(status);
            itemRepo.save(item.get());
            return item;
        }else {
            log.error("Exception when item buy! Method : buyItem "+id+", class: ItemServiceImpl");
            return Optional.empty();
        }
    }

    @Override
    public List<Item> findAllBuyItems() {
        List<Item> items = itemRepo.findAllBuyItems("BUY");
        if(items.size()==0){
            log.warn("Items list is null! Method: findAllBuyItems , class: ItemServiceImpl");
        }else{
            log.info("Items list");
        }
        return items;
    }

    @Override
    public List<Item> findAllByInaccurateMatchNameItem(String value) {
        List<Item> itemList = itemRepo.findAllByInaccurateMatchNameItem(value);
        return itemList;
    }

    @Override
    public List<Item> findAllByInaccurateMatchProductGroup(String value) {
        List<Item> itemList = itemRepo.findAllByInaccurateMatchProductGroup(value);
        return itemList;
    }


}

package simbirSoftPractice.demo.service.interfaces;

import org.springframework.http.ResponseEntity;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<Item> findAll();

    ResponseEntity<Item> getById(Long id);

    void save(ItemDto newItem);

    ResponseEntity<String> deleteById(Long id);
}

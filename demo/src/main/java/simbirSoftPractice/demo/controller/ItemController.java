package simbirSoftPractice.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dto.ItemDto;
import simbirSoftPractice.demo.service.implement.ItemServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Item resources", description = "crud operations")
public class ItemController {

    @Autowired
    private final ItemServiceImpl itemService;

    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/sayHello")
    @ApiOperation(value = "show Item", response = ItemDto.class)
    public ItemDto getHelloWorld(){
        ItemDto itemDto = new ItemDto();
        itemDto.setName("my Name");
        return itemDto;
    }

    @PostMapping("/newItem")
    @ApiOperation(value = "add new Item", response = ItemDto.class)
    public ResponseEntity<ItemDto> newItem(@RequestBody ItemDto itemDto){
        itemService.save(itemDto);
        return ResponseEntity.ok(itemDto);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "show list Items", response = Item.class)
    public List<Item> findAll(){
        return itemService.findAll();
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "delete Item", response = Item.class)
    public ResponseEntity<Item> deleteById(@PathVariable Long id){
        Item deleteItem = itemService.getById(id);
        if (deleteItem != null) {
            itemService.deleteById(id);
            return ResponseEntity.ok(deleteItem);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "find Item by id", response = Item.class)
    public Item getById(@PathVariable Long id){
        return itemService.getById(id);
    }
}

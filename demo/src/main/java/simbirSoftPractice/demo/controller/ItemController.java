package simbirSoftPractice.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dto.ItemBuyDto;
import simbirSoftPractice.demo.dto.ItemDto;
import simbirSoftPractice.demo.service.interfaces.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Item resources", description = "crud operations")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/newItem")
    @ApiOperation(value = "add new Item", response = ItemDto.class)
    public ResponseEntity<String> newItem(@RequestBody ItemDto itemDto){
        itemService.save(itemDto);
        return ResponseEntity.ok("successfully save!");
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "show list Items", response = Item.class)
    public List<Item> findAll(){
        return itemService.findAll();
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "delete Item", response = Item.class)
    public ResponseEntity<Item> deleteById(@PathVariable Long id){
       Item item = itemService.deleteById(id).get();
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "find Item by id", response = Item.class)
    public ResponseEntity<Item> getById(@PathVariable Long id){
        Item item = itemService.getById(id).get();
        return ResponseEntity.ok(item);
    }

    @PostMapping("/buy/{id}")
    @ApiOperation(value = "buy item ", response = Item.class)
    public ResponseEntity<Item> buyItem(@PathVariable Long id){
        Item item = itemService.buyItem(id).get();
        return ResponseEntity.ok(item);
    }

    @GetMapping("/buyItemList")
    public ResponseEntity<List<Item>> listBuyItem(){
        List<Item> itemBuyDtoList = itemService.findAllBuyItems();
        return ResponseEntity.ok(itemBuyDtoList);
    }
}

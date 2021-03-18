package simbirSoftPractice.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dto.ShopDto;
import simbirSoftPractice.demo.service.interfaces.ShopWithItemsService;

import java.util.List;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopWithItemsService itemsService;

    @PostMapping("/list")
    public ResponseEntity<List<Item>> findAllInShop(@RequestBody ShopDto shopDto){
        List<Item> itemList = itemsService.findByShop(shopDto);
        return ResponseEntity.ok(itemList);
    }

    @PostMapping("/listBuyItems")
    public ResponseEntity<List<Item>> findAllBuyItems(@RequestBody ShopDto shopDto){
        List<Item> itemList = itemsService.findAllBuyItemsInShop(shopDto);
        return ResponseEntity.ok(itemList);
    }

    @PostMapping("/listDebitedItems")
    public ResponseEntity<List<Item>> findAllDebitedItems(@RequestBody ShopDto shopDto){
        List<Item> itemList = itemsService.findAllDebitedItemsInShop(shopDto);
        return ResponseEntity.ok(itemList);
    }

    @PostMapping("/shopEarnings")
    public ResponseEntity<ShopDto> findEarningsShop(@RequestBody ShopDto shopDto){
        ShopDto responseShop = itemsService.findEarningsShop(shopDto);
        return ResponseEntity.ok(responseShop);
    }

    @PostMapping("/middleEarnings")
    public ResponseEntity<ShopDto> middleEarningsReport(@RequestBody ShopDto shopDto){
        ShopDto responseShop = itemsService.findMiddleBuyInShop(shopDto);
        return ResponseEntity.ok(responseShop);
    }
}

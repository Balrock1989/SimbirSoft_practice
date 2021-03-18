package simbirSoftPractice.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.PackageDebited;
import simbirSoftPractice.demo.service.interfaces.PackageDebitedService;

import java.util.List;

@RestController
@RequestMapping("/debited")
@RequiredArgsConstructor
@Api(value = "package for debited",description = "collect all items and debited them")
public class PackageDebitedController {


    private final PackageDebitedService debitedService;

    @PostMapping("/add/{id}")
    @ApiOperation(value = "chose items for debited",response = PackageDebited.class)
    public ResponseEntity<Item> addItem(@PathVariable Long id){
        Item itemDebited = debitedService.addItem(id).get();
        return ResponseEntity.ok(itemDebited);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "delete items from package if they go by error", response = PackageDebited.class)
    public ResponseEntity<PackageDebited> deleteItem(@PathVariable Long id){
        PackageDebited itemDebited = debitedService.deleteItem(id).get();
        return ResponseEntity.ok(itemDebited);
    }

    @GetMapping("/list")
    @ApiOperation(value = "1 part debited , list all items prepared for debited",response = PackageDebited.class)
    public ResponseEntity<List<PackageDebited>> findAll(){
        List<PackageDebited> debitedList = debitedService.findAll();
        return ResponseEntity.ok(debitedList);
    }

    @PostMapping("/confirm")
    @ApiOperation(value = "2 part debited, confirm all items",response = PackageDebited.class)
    public ResponseEntity<List<PackageDebited>> confirmMethodDebited(){
        List<PackageDebited> debitedList = debitedService.listToDebited();
        return ResponseEntity.ok(debitedList);
    }
}

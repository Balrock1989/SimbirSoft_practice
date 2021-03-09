package simbirSoftPractice.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simbirSoftPractice.demo.dao.entity.PackageDebited;
import simbirSoftPractice.demo.service.interfaces.PackageDebitedService;

import java.util.List;

@RestController
@RequestMapping("/debited")
@RequiredArgsConstructor
@Api(value = "package for debited",description = "collect all items and debited them")
public class PackageDebitedController {

    @Autowired
    private final PackageDebitedService debitedService;

    @PostMapping("/add/{id}")
    @ApiOperation(value = "chose items for debited",response = PackageDebited.class)
    public ResponseEntity<String> addItem(@PathVariable Long id){
        return debitedService.addItem(id);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "delete items from package if they go by error", response = PackageDebited.class)
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        return debitedService.deleteItem(id);
    }

    @GetMapping("/list")
    @ApiOperation(value = "1 part debited , list all items prepared for debited",response = PackageDebited.class)
    public ResponseEntity<List<PackageDebited>> findAll(){
        return debitedService.findAll();
    }

    @PostMapping("/confirm")
    @ApiOperation(value = "2 part debited, confirm all items",response = PackageDebited.class)
    public ResponseEntity<String> confirmMethodDebited(){
        return debitedService.listToDebited();
    }
}

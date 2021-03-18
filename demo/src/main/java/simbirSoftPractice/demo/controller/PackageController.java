package simbirSoftPractice.demo.controller;

import com.sun.xml.bind.v2.runtime.reflect.Lister;
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
import simbirSoftPractice.demo.dao.entity.Package;
import simbirSoftPractice.demo.service.interfaces.PackageService;

import java.util.List;

@RestController
@RequestMapping("/package")
@RequiredArgsConstructor
@Api(value = "Package for buy", description = "chosen items storage for buy")
public class PackageController {

    @Autowired
    private final PackageService packageService;

    @GetMapping("/list")
    @ApiOperation(value = "list all items",response = Package.class)
    public ResponseEntity<List<Package>> findAll(){
        List<Package> packageList = packageService.findAll();
        return ResponseEntity.ok(packageList);
    }

    @PostMapping("/add/{id}")
    @ApiOperation(value = "add item in package", response = Package.class)
    public ResponseEntity<Item> addItem(@PathVariable Long id){
        Item aPackage = packageService.addItem(id).get();
        return ResponseEntity.ok(aPackage);
    }

    @PostMapping("/clearList")
    @ApiOperation(value = "clear list if customer cancel all items", response = Package.class)
    public ResponseEntity<List<Package>> clearList(){
        List<Package> packageList = packageService.deleteAllList();
        return ResponseEntity.ok(packageList);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "delete item from package if customer want so",response = Package.class)
    public ResponseEntity<Package> deleteFromPackage(@PathVariable Long id){
        Package aPackage = packageService.deleteItem(id).get();
        return ResponseEntity.ok(aPackage);
    }

    @PostMapping("/packageBuy")
    @ApiOperation(value = "buy all items in package", response = Package.class)
    public ResponseEntity<List<Package>> buyPackage(){
        List<Package> packageList = packageService.buyItems();
        return ResponseEntity.ok(packageList);
    }
}

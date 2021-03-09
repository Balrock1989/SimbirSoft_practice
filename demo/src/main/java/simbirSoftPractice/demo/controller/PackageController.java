package simbirSoftPractice.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simbirSoftPractice.demo.dao.entity.Package;
import simbirSoftPractice.demo.dto.ItemPackageDto;
import simbirSoftPractice.demo.service.interfaces.PackageService;

import java.util.List;

@RestController
@RequestMapping("/package")
@RequiredArgsConstructor
public class PackageController {

    @Autowired
    private final PackageService packageService;

    @GetMapping("/list")
    public ResponseEntity<List<Package>> findAll(){
        return packageService.findAll();
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<String> addItem(@PathVariable Long id){
        return packageService.addItem(id);
    }

    @PostMapping("/clearList")
    public ResponseEntity<String> clearList(){
        return packageService.deleteAllList();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteFromPackage(@PathVariable Long id){
        return packageService.deleteItem(id);
    }

    @PostMapping("/packageBuy")
    public ResponseEntity<String> buyPackage(){
        return packageService.buyItems();
    }
}

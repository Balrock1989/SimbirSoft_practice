package simbirSoftPractice.demo.controller;

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
public class PackageDebitedController {

    @Autowired
    private final PackageDebitedService debitedService;

    @PostMapping("/add/{id}")
    public ResponseEntity<String> addItem(@PathVariable Long id){
        return debitedService.addItem(id);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        return debitedService.deleteItem(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PackageDebited>> findAll(){
        return debitedService.findAll();
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmMethodDebited(){
        return debitedService.listToDebited();
    }
}

package simbirSoftPractice.demo.service.interfaces;

import org.springframework.http.ResponseEntity;
import simbirSoftPractice.demo.dao.entity.Package;
import simbirSoftPractice.demo.dto.ItemPackageDto;

import java.util.List;

public interface PackageService {

    ResponseEntity<String> addItem(Long id);

    ResponseEntity<String> deleteItem(Long id);

    ResponseEntity<String> deleteAllList();

    ResponseEntity<List<Package>> findAll();

    ResponseEntity<String> buyItems();
}

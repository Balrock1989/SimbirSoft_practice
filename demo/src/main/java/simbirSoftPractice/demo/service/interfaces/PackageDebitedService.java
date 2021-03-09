package simbirSoftPractice.demo.service.interfaces;

import org.springframework.http.ResponseEntity;
import simbirSoftPractice.demo.dao.entity.PackageDebited;

import java.util.List;

public interface PackageDebitedService {

    ResponseEntity<String> addItem(Long id);

    ResponseEntity<String> deleteItem(Long id);

    ResponseEntity<String> listToDebited();

    ResponseEntity<List<PackageDebited>> findAll();
}

package simbirSoftPractice.demo.service.interfaces;

import org.springframework.http.ResponseEntity;
import simbirSoftPractice.demo.dao.entity.PackageDebited;

import java.util.List;

public interface PackageDebitedService {

    ResponseEntity<PackageDebited> addItem(Long id);

    ResponseEntity<PackageDebited> deleteItem(Long id);

    ResponseEntity<List<PackageDebited>> listToDebited();

    ResponseEntity<List<PackageDebited>> findAll();
}

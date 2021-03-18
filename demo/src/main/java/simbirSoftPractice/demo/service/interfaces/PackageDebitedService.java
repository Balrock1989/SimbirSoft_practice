package simbirSoftPractice.demo.service.interfaces;

import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.PackageDebited;

import java.util.List;
import java.util.Optional;

public interface PackageDebitedService {

    Optional<Item> addItem(Long id);

    Optional<PackageDebited> deleteItem(Long id);

    List<PackageDebited> listToDebited();

    List<PackageDebited> findAll();
}

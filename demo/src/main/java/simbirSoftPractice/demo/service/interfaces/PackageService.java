package simbirSoftPractice.demo.service.interfaces;

import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Package;

import java.util.List;
import java.util.Optional;

public interface PackageService {

    Optional<Item> addItem(Long id);

    Optional<Package> deleteItem(Long id);

    List<Package> deleteAllList();

    List<Package> findAll();

    List<Package> buyItems();
}

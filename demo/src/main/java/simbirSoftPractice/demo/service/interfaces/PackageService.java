package simbirSoftPractice.demo.service.interfaces;

import simbirSoftPractice.demo.dao.entity.Package;

import java.util.List;

public interface PackageService {

    Package addItem(Long id);

    Package deleteItem(Long id);

    List<Package> deleteAllList();

    List<Package> findAll();

    List<Package> buyItems();
}

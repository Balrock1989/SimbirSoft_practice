package simbirSoftPractice.demo.service.interfaces;

import simbirSoftPractice.demo.dao.entity.PackageDebited;

import java.util.List;

public interface PackageDebitedService {

    PackageDebited addItem(Long id);

    PackageDebited deleteItem(Long id);

    List<PackageDebited> listToDebited();

    List<PackageDebited> findAll();
}

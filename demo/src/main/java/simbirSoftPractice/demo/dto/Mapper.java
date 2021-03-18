package simbirSoftPractice.demo.dto;

import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Package;
import simbirSoftPractice.demo.dao.entity.PackageDebited;

public class Mapper {

    public PackageDebited itemToPackageDebited(Item item){
        PackageDebited packageDebited = new PackageDebited();
        packageDebited.setItemId(item.getId());
        packageDebited.setName(item.getName());
        packageDebited.setPrice(item.getPrice());
        packageDebited.setQuantity(item.getQuantity());
        packageDebited.setStatus(item.getStatus().getName());
        return packageDebited;
    }

    public Package itemToPackage(Item item){
        Package aPackage = new Package();
        aPackage.setItemId(item.getId());
        aPackage.setName(item.getName());
        aPackage.setPrice(item.getPrice());
        aPackage.setQuantity(item.getQuantity());
        aPackage.setShopName(item.getShopName());
        return aPackage;
    }
}

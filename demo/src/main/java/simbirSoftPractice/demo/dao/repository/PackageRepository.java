package simbirSoftPractice.demo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simbirSoftPractice.demo.dao.entity.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package,Long> {
}

package simbirSoftPractice.demo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simbirSoftPractice.demo.dao.entity.PackageDebited;

@Repository
public interface PackageDebitedRepository extends JpaRepository<PackageDebited,Long> {
}

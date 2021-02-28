package simbirSoftPractice.demo.service.implement;

import simbirSoftPractice.demo.dao.entity.Compane;

import java.util.List;

public interface CompaneService {

    List<Compane> findAll();

    Compane getById(Long id);

    void save(Compane compane);

    void deleteById(Long id);
}

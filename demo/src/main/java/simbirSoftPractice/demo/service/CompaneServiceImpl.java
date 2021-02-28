package simbirSoftPractice.demo.service;

import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Compane;
import simbirSoftPractice.demo.service.implement.CompaneService;

import java.util.List;

@Service
public class CompaneServiceImpl implements CompaneService {
    @Override
    public List<Compane> findAll() {
        return null;
    }

    @Override
    public Compane getById(Long id) {
        return null;
    }

    @Override
    public void save(Compane compane) {

    }

    @Override
    public void deleteById(Long id) {

    }
}

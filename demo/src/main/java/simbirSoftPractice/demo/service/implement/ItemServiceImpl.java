package simbirSoftPractice.demo.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simbirSoftPractice.demo.dao.entity.Company;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Status;
import simbirSoftPractice.demo.dao.repository.CompanyRepository;
import simbirSoftPractice.demo.dao.repository.ItemRepository;
import simbirSoftPractice.demo.dto.ItemDto;
import simbirSoftPractice.demo.service.interfaces.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemRepository itemRepo;

    @Autowired
    private final CompanyRepository companyRepo;

    public ItemServiceImpl(ItemRepository itemRepo, CompanyRepository companyRepo) {
        this.itemRepo = itemRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public List<Item> findAll() {
        return (List<Item>) itemRepo.findAll();
    }

    @Override
    public Item getById(Long id) {
        return itemRepo.findById(id).get();
    }

    @Override
    public void save(ItemDto newItem) {
        Item item = new Item();
        item.setName(newItem.getName());
        item.setPrice(newItem.getPrice());
        item.setQuantity(newItem.getQuantity());
        Status status = new Status();
        status.setName(newItem.getStatus());
        item.setStatus(status);
        Iterable<Company> companies = companyRepo.findAll();
        companies.forEach(company -> {
                    if (company.getName().toLowerCase().equals(newItem.getCompanyDto().toLowerCase())) {
                        item.setCompany(company);
                        System.out.println("-----OLD COMPANY------: " + item.toString());
                        itemRepo.save(item);
                        return;
                    }
        });

                Company newCompany = new Company();
                newCompany.setName(newItem.getCompanyDto());
                item.setCompany(newCompany);
                System.out.println("----NEW COMPANY----:" + item.toString());
                itemRepo.save(item);
                return;

    }

    @Override
    public void deleteById(Long id) {
        itemRepo.deleteById(id);
    }
}

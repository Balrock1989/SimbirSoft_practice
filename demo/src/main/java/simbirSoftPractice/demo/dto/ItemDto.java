package simbirSoftPractice.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import simbirSoftPractice.demo.dao.entity.Company;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Status;


@ApiModel(value = "class Item")
public class ItemDto {

    @JsonIgnore
    private Long id;
    @ApiModelProperty(value = "name of Item", example = "phone")
    private String name;
    @ApiModelProperty(value = "price of Item", example = "12000")
    private int price;
    @ApiModelProperty(value = "quantity of Item", example = "5")
    private int quantity;
    @ApiModelProperty(value = "status of Item", example = "ONSTORAGE")
    private String status = "ONSTORAGE";
    @ApiModelProperty(value = "company of Item", example = "samsung")
    private String companyDto;

    public ItemDto() {
    }

    public Item itemDtoToItem(ItemDto newItem){
        Item item = new Item();
        item.setName(newItem.getName());
        item.setPrice(newItem.getPrice());
        item.setQuantity(newItem.getQuantity());
        Status status = new Status();
        status.setName(newItem.getStatus());
        item.setStatus(status);
        Company newCompany = new Company();
        newCompany.setName(newItem.getCompanyDto());
        item.setCompany(newCompany);
        return item;
    }

    public ItemDto(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyDto() {
        return companyDto;
    }

    public void setCompanyDto(String companyDto) {
        this.companyDto = companyDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", status=" + status +
                ", companyDto='" + companyDto + '\'' +
                '}';
    }
}

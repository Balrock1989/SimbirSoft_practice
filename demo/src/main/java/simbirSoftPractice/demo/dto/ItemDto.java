package simbirSoftPractice.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import simbirSoftPractice.demo.dao.entity.Company;
import simbirSoftPractice.demo.dao.entity.Item;
import simbirSoftPractice.demo.dao.entity.Status;


@ApiModel(value = "class Item")
@Getter
@Setter
@NoArgsConstructor
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
    @ApiModelProperty(value = "shop name where item", example = "m.video")
    private String shopName;
    private String value;

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
        item.setShopName(newItem.getShopName());
        return item;
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

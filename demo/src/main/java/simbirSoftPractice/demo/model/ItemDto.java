package simbirSoftPractice.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "class Item")
public class ItemDto {

    @JsonIgnore
    private Long id;
    @ApiModelProperty(value = "name of Item", example = "phone")
    private String name;
    private int price;
    private int quantity;

    public ItemDto() {
    }

    public ItemDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

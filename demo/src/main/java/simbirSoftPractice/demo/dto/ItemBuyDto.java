package simbirSoftPractice.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"name","price","quantity","status"})
public class ItemBuyDto {

    @JsonIgnore
    private Long id;

    private String name;
    private int price;
    private int quantity;
    private String status;
}

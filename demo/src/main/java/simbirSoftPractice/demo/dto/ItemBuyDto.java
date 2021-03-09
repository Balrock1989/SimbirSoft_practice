package simbirSoftPractice.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

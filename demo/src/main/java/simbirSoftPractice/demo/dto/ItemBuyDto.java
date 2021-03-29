package simbirSoftPractice.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemBuyDto {

    @JsonIgnore
    private Long id;

    private String name;
    private int price;
    private int quantity;
    private String status;
}

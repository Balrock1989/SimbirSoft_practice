package simbirSoftPractice.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemPackageDto {

    @JsonIgnore
    private Long id;

    private String name;

    private int price;

    private int quantity;


}

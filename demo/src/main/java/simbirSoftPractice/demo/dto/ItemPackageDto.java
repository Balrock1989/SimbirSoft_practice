package simbirSoftPractice.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemPackageDto {

    @JsonIgnore
    private Long id;

    private String name;

    private int price;

    private int quantity;


}

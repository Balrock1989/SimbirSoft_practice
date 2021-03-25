package simbirSoftPractice.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShopDto {

    private String shopName;
    private String status;
    private int earnings;
    private int debitedItems;
    private int middlePrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTimeStart = LocalDateTime
                    .of(1970,1,1,
                            1,1,1,1);
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTimeEnd = LocalDateTime.now();


}

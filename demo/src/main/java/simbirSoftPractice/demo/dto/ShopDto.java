package simbirSoftPractice.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

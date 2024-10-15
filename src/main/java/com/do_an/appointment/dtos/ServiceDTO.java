package com.do_an.appointment.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {
    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("insurance_price")
    private Float insurancePrice;

}

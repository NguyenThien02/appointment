package com.do_an.appointment.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDetalDTO {
    @JsonProperty("profile_id")
    private Long profileId;

    @JsonProperty("service_id")
    private Long serviceId;

    @JsonProperty("price")
    private Float price;
}

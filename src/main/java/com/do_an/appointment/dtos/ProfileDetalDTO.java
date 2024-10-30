package com.do_an.appointment.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDetalDTO {
    @JsonProperty("profile_id")
    private Long profileId;

    @JsonProperty("service_ids")
    private List<Long> serviceIds;
}

package com.do_an.appointment.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessengerResponse {
    @JsonProperty("messenger")
    private String messenger;
}

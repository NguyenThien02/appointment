package com.do_an.appointment.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("token")
    private String token;
}
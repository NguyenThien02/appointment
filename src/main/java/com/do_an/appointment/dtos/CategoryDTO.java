package com.do_an.appointment.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDTO {
    @NotEmpty(message = "Category's name cannot be empty")
    private String name;
}


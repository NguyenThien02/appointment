package com.do_an.appointment.models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@Table(name="categories")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}

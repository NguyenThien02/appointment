package com.do_an.appointment.models;

import jakarta.persistence.*;
import lombok.*;

@Table(name="services")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Service extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "insurance_price")
    private Float insurancePrice;
}

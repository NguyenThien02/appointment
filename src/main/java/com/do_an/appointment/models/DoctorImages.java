package com.do_an.appointment.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="doctor_images")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "image_url", length = 300)
    private String imageUrl;
}

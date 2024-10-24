package com.do_an.appointment.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="doctors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @Column(name = "experience")
    private Long experience;

    @Column(name = "image_url")
    private String imageUrl;
}

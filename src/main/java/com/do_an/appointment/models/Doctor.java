package com.do_an.appointment.models;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "experience")
    private Long experience;
}

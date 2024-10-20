package com.do_an.appointment.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date", nullable = false)
    private Date date;

}

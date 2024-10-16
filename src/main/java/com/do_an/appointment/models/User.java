package com.do_an.appointment.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}

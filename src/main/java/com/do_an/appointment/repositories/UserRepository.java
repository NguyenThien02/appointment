package com.do_an.appointment.repositories;

import com.do_an.appointment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber); // Kiểm tra user có phone number này có tồn tại hay không

    //SELECT * FROM users WHERE phone_number=?
    Optional<User> findByPhoneNumber(String phoneNumber);
}
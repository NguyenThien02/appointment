package com.do_an.appointment.repositories;

import com.do_an.appointment.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber); // Kiểm tra user có phone number này có tồn tại hay không

    //SELECT * FROM users WHERE phone_number=?
    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM User u WHERE u.role.id = :roleId")
    Page<User> findByRoleId(@Param("roleId") Long roleId, Pageable pageable);

}
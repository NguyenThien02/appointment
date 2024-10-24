package com.do_an.appointment.repositories;

import com.do_an.appointment.models.Doctor;
import com.do_an.appointment.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

//    @Query("SELECT u FROM Docter u WHERE u.user.id = :userId")
//    Page<User> findByUserId(@Param("roleId") Long roleId, Pageable pageable);
}

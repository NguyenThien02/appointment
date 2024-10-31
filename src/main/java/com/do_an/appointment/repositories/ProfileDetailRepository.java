package com.do_an.appointment.repositories;

import com.do_an.appointment.models.ProfileDetail;
import com.do_an.appointment.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileDetailRepository extends JpaRepository<ProfileDetail, Long> {
    @Query("SELECT pd.service FROM ProfileDetail pd WHERE pd.profile.id = :profileId")
    List<Service> findServicesByProfileId(@Param("profileId") Long profileId);
}

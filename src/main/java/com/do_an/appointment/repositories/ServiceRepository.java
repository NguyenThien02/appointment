package com.do_an.appointment.repositories;

import com.do_an.appointment.models.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query("SELECT s FROM Service s WHERE " +
            "(:categoryId IS NULL OR :categoryId = 0 OR s.category.id = :categoryId) " +
            "AND (:keyword IS NULL OR :keyword = '' OR s.name LIKE %:keyword% OR s.description LIKE %:keyword%)")
    Page<Service> searchServices
            (@Param("keyword") String keyword,
             @Param("categoryId") Long categoryId,
             Pageable pageable);

    List<Service> findAllByIdIn(List<Long> ids);
}


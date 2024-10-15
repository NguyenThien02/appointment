package com.do_an.appointment.repositories;

import com.do_an.appointment.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
